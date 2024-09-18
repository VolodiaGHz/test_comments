package com.git.volodia.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String username;
    private String fullName;

    @JsonCreator
    public User(@JsonProperty("id") int id,
                @JsonProperty("username")String username,
                @JsonProperty("fullName")String fullName) {
        this.id = id;
        this.username = username.substring(0,1).toUpperCase()+username.substring(1);
        this.fullName = fullName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUiName(){
        return this.fullName+"("+this.username+")";
    }
}
