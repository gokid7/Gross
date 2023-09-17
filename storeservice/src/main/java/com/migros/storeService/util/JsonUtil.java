package com.migros.storeService.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convertFileToString(String path){
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(path)){
            JsonNode jsonNode = objectMapper.readValue(in, JsonNode.class);
            String jsonString = objectMapper.writeValueAsString(jsonNode);
            System.out.println(jsonString);
            return jsonString;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
