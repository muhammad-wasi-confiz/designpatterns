package org.confiz.chain.handlers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.confiz.chain.Response;

public class JsonResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;

    @Override
    public void setNextHandler(ResponseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public Object process(Response response, String query) throws Exception {
        boolean compatible = "application/json".equalsIgnoreCase(response.getType());

        if (!compatible && nextHandler != null) {
            return nextHandler.process(response, query);
        }

        if (response.getBody() != null) {
            DocumentContext ctx = JsonPath.parse(response.getBody());

            return ctx.read(query);
        }

        return null;

    }
}
