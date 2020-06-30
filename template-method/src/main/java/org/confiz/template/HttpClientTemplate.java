package org.confiz.template;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import javax.ws.rs.core.Response.Status.Family;

public abstract class HttpClientTemplate {
    private CloseableHttpClient client;

    public HttpClientTemplate(CloseableHttpClient client) {
        this.client = client;
    }

    abstract void handleResponse(HttpResponse response);

    public void execute(HttpUriRequest request) throws Exception {
        validateRequest(request);

        try (CloseableHttpResponse response = client.execute(request)) {
            Family statusCodeFamily = Family.familyOf(response.getStatusLine().getStatusCode());

            boolean isSuccess = Family.SUCCESSFUL == statusCodeFamily;
            if (isSuccess) {
                handleResponse(response);
            }
        }
    }

    private void validateRequest(HttpRequest request) {
        // Validate request
    }
}
