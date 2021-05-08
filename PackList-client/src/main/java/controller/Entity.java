package controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Entity {

    public static final String BASE_URL = "http://localhost:8080/";

    public static HttpEntity createJSONEntity(Object object){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return entity;
    }
}
