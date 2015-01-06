package com.amithmit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interactions.commands.APIRequest;

import java.io.IOException;

public class EventSerDeserializer {

    public ObjectMapper objectMapper = new ObjectMapper();

    public String serialize(APIRequest request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new SymphonyException("Could not deserialize request type " + /*request.getType() */
                    ":" + e.getMessage());
        }
    }

//    public static String serialize(ResponseEvent response) {
//        try {
//            return objectMapper.writeValueAsString(response);
//        } catch (JsonProcessingException e) {
//            throw new SymphonyException("Could not deserialize response " + response.getType());
//        }
//    }

    public <T> T deSerialize(String message, Class<T> classz) {
        try {
            return objectMapper.readValue(message.getBytes(), classz);
        } catch (IOException e) {
            throw new SymphonyException(e.getMessage());
        }
    }
}
