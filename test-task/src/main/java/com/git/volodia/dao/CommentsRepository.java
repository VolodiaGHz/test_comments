package com.git.volodia.dao;

import com.git.volodia.pojo.Comment;

import java.util.List;

public interface CommentsRepository {

    void saveComment(List<Comment> comments) throws Exception ;

    List<Comment> getComments() throws Exception ;

}
