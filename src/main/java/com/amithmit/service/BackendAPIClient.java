package com.amithmit.service;

import com.interactions.commands.APIRequest;
import com.interactions.commands.StatusResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by voicestreams on 1/6/15.
 */
@Component
public class BackendAPIClient {

    private final DefaultHttpClient defaultHTTPClient;
    private EventSerDeserializer serDeserializer;

    @Autowired
    public BackendAPIClient(DefaultHttpClient defaultHttpClient, EventSerDeserializer serDeserializer) {
        this.defaultHTTPClient = defaultHttpClient;
        this.serDeserializer = serDeserializer;
    }

    public StatusResponse createAggregate(APIRequest request) {
        String jsonOut = null;
        StatusResponse responseStatus = null;
        String jsonIn = serDeserializer.serialize(request);
        HttpPost postRequest = new HttpPost("http://localhost:8080/report");
        try {
                StringEntity input = new StringEntity(jsonIn);
                input.setContentType("application/json");
                postRequest.setEntity(input);
                HttpResponse response = defaultHTTPClient.execute(postRequest);

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                StringBuilder builder = new StringBuilder();
                String output = null;
                while ((output = br.readLine()) != null) {
                    builder.append(output);
                }

                jsonOut = builder.toString();
                responseStatus = serDeserializer.deSerialize(jsonOut, StatusResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStatus;
    }

    public StatusResponse checkStatus(String id) {
        String jsonOut = null;
        StatusResponse responseStatus = null;
        HttpGet getRequest = new HttpGet("http://localhost:8080/status/" + id);
        try {
            HttpResponse response = defaultHTTPClient.execute(getRequest);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            StringBuilder builder = new StringBuilder();
            String output = null;
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }

            jsonOut = builder.toString();
            responseStatus = serDeserializer.deSerialize(jsonOut, StatusResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStatus;
    }

//    public String[] fetchTopXWebsites(String resourceId, FetchTopXRequest request) {
//        String jsonOut = null;
//        StatusResponse responseStatus = null;
//        String jsonIn = serDeserializer.serialize(request);
//        HttpPost postRequest = new HttpPost("http://localhost:8080/report/" + resourceId);
//        try {
//            StringEntity input = new StringEntity(jsonIn);
//            input.setContentType("application/json");
//            postRequest.setEntity(input);
//            HttpResponse response = defaultHTTPClient.execute(postRequest);
//
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader((response.getEntity().getContent())));
//
//            StringBuilder builder = new StringBuilder();
//            String output = null;
//            while ((output = br.readLine()) != null) {
//                builder.append(output);
//            }
//
//            jsonOut = builder.toString();
//            responseStatus = serDeserializer.deSerialize(jsonOut, StatusResponse.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return responseStatus;
//    }

}
