package org.confiz.chain.handlers;

import org.confiz.chain.Response;

public interface ResponseHandler {
    void setNextHandler(ResponseHandler nextHandler);

    Object process(Response response, String query) throws Exception;

}
