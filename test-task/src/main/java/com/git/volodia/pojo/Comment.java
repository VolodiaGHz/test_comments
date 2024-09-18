package com.git.volodia.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private int id;
    private String body;
    private int postId;
    private String updatedAt;
    private String username;

    public Comment() {
    }

    @JsonCreator
    public Comment(
           @JsonProperty("id") int id,
           @JsonProperty("body")  String body,
           @JsonProperty("postId") int postId,
           @JsonProperty("user") User user) {
        this.id = id;
        this.body = body;
        this.postId = postId;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.updatedAt = sdf.format(new Date());
        this.username = user.getUsername();
    }

    public Comment(int id, String body, int postId, String updatedAt,
                   String username) {
        this.id = id;
        this.body = body;
        this.postId = postId;
        this.updatedAt = updatedAt;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
