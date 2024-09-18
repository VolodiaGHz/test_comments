package com.git.volodia.dao;

import com.git.volodia.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static com.git.volodia.utils.SQLUtils.getStringFromResource;

@Repository
public class CommentsRepositoryImpl implements CommentsRepository {

    @Value("classpath:sql/get_comments.sql")
    Resource get_comments_resource;

    @Value("classpath:sql/save_comment.sql")
    Resource save_comment_resource;

    final
    JdbcTemplate jdbcTemplate;

    public CommentsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveComment(List<Comment> comments) throws Exception {
        try {
            MapSqlParameterSource[] batchParams = comments.stream()
                    .map(entity -> new MapSqlParameterSource()
                            .addValue("id", entity.getId())
                            .addValue("body", entity.getBody())
                            .addValue("postId", entity.getPostId())
                            .addValue("username", entity.getUsername())
                            .addValue("updatedAt", entity.getUpdatedAt())
                    )
                    .toArray(MapSqlParameterSource[]::new);
            new NamedParameterJdbcTemplate(jdbcTemplate).batchUpdate(getStringFromResource(save_comment_resource), batchParams);
        } catch (Throwable e) {
            throw new Exception("Error while saving data to db", e);

        }
    }

    @Override
    public List<Comment> getComments() throws Exception {
        try {
            return new NamedParameterJdbcTemplate(jdbcTemplate)
                    .query(getStringFromResource(get_comments_resource),
                            new MapSqlParameterSource(), new RowMapper<Comment>() {
                                @Override
                                public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                                    return new Comment(
                                            resultSet.getInt("id"),
                                            resultSet.getString("body"),
                                            resultSet.getInt("postId"),
                                            resultSet.getString("updatedAt"),
                                            resultSet.getString("username")
                                    );
                                }
                            });
        } catch (Throwable e) {
            throw new Exception("Error while getting data from db", e);
        }
    }
}
