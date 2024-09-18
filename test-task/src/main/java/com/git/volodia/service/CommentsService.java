package com.git.volodia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.volodia.dao.CommentsRepository;
import com.git.volodia.pojo.Comment;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CommentsService {

    @Value("${external.link}")
    private String externalLink;

    final RestTemplate restTemplate;
    final CommentsRepository commentsRepository;

    public CommentsService(RestTemplate restTemplate, CommentsRepository commentsRepository) {
        this.restTemplate = restTemplate;
        this.commentsRepository = commentsRepository;
    }

    public List<Comment> loadAndSave() throws Exception {
        try {
            List<Comment> comments = loadExternal();
            if (comments != null && !comments.isEmpty()) {
                commentsRepository.saveComment(comments);
            }
            return loadSavedComments();
        }catch (Throwable e){
            throw new Exception("Error while processing comments",e);

        }

    }

    public List<Comment> loadSavedComments() throws Exception {
        return commentsRepository.getComments();
    }

    public List<Comment> loadExternal() throws Exception {
        HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        String result = restTemplate.exchange(externalLink, HttpMethod.GET, requestEntity, String.class, new HashMap<>()).getBody();
        return parseResponse(result);
    }

    private List<Comment> parseResponse(String response) throws Exception {
        try {
            List<Comment> comments = new ArrayList<>();
            JSONObject resultJSON = new JSONObject(response);
            if (resultJSON.has("comments")){
                ObjectMapper om = new ObjectMapper();
                Comment[] parsedComments = om.readValue(resultJSON.getJSONArray("comments").toString(),Comment[].class);
                comments = Arrays.asList(parsedComments);
            }
            return comments;
        }catch (Throwable e){
            throw new Exception("Error while parsing response from link:"+externalLink,e);
        }

    }
}
