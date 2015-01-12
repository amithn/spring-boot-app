package com.amithmit.app;



import com.interactions.commands.Event;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.protocol.BasicHttpContext;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * This example demonstrates a fully asynchronous execution of multiple HTTP exchanges
 * where the result of an individual operation is reported using a callback interface.
 */
public class AsyncClientHttpExchangeFutureCallback {

    public static void main(final String[] args) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();


        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[] {
                    new HttpGet("http://localhost:8080/report1"),
                    new HttpGet("http://localhost:8080/report2"),
                    new HttpGet("http://localhost:8080/report3"),
            };
            final CountDownLatch latch = new CountDownLatch(requests.length);
            int counter = 0;
            for (final HttpGet request: requests) {
                final BasicHttpContext context = new BasicHttpContext();
                context.setAttribute("id", "report" + ++counter + "---" + UUID.randomUUID().toString());
                context.setAttribute("event", Event.EV_CHART_CREATED);
                httpclient.execute(request, context, new FutureCallback<HttpResponse>() {

                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        System.out.println(" ******** Context value was ******** " + context.getAttribute("id"));
                        System.out.println(" ******** Context value was ******** " + context.getAttribute("event"));
                        //System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                    }

                    public void failed(final Exception ex) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->" + ex);
                    }

                    public void cancelled() {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + " cancelled");
                    }

                });
            }
            latch.await();
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

}