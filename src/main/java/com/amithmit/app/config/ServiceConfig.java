package com.amithmit.app.config;

import com.amithmit.service.EventSerDeserializer;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by voicestreams on 1/6/15.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.amithmit.service"})
public class ServiceConfig {

    /* DefaultHttpClient has been deprecated TODO: Use some other */
    @Bean
    public DefaultHttpClient defaultHTTPClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        return client;
    }

    @Bean
    public EventSerDeserializer eventSerDeserializer() {
        return new EventSerDeserializer();
    }
}
