package org.confiz.template;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.confiz.template.AbstractTimeLoggingTask;
import org.confiz.template.HttpClientTemplate;
import org.confiz.template.TimeLoggingTask;
import org.junit.Test;

import java.io.IOException;

public class TemplateMethodTest {

    @Test
    public void testTimeLogging() {
        TimeLoggingTask timeLoggingTask = new AbstractTimeLoggingTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        timeLoggingTask.runWithTimeLogging();
    }

    @Test
    public void testHttpClient() throws Exception {
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate(HttpClientBuilder.create().build()) {
            @Override
            void handleResponse(HttpResponse response) {
                try {
                    System.out.println(EntityUtils.toString(response.getEntity()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        HttpGet httpGet = new HttpGet("https://httpbin.org/get");
        httpClientTemplate.execute(httpGet);
    }
}
