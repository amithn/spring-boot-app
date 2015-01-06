package com.amithmit.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interactions.commands.IndustryWebsiteRequest;
import com.interactions.commands.IndustryWebsiteRequestBuilder;
import com.interactions.commands.ReportType;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class HttpCommonsClientTest {

    public static void main(String[] args) {

        Date dateFrom = new Date(1399075200000L);
        Date dateTo = new Date(1399680000000L);

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet getRequest = new HttpGet(
//                    "http://localhost:8080/hello");
//            getRequest.addHeader("accept", "application/json");


            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/report");

            IndustryWebsiteRequest request = new IndustryWebsiteRequestBuilder()
                                                    .withExpression("HWVISITS: 'www.lol.com'")
                                                    .withRegion("AU")
                                                    .withSubject(1)
                                                    .withDateFrom("2014-07-12")
                                                    .withDateTo("2014-07-12")
                                                    .withType(ReportType.INDUSTRY_WEBSITES)
                                                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(request);

            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

           // HttpResponse response = httpClient.execute(request);

//            if (response.getStatusLine().getStatusCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatusLine().getStatusCode());
//            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}