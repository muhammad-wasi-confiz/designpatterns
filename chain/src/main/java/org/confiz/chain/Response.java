package org.confiz.chain;

public class Response {
    private String type;
    private String body;

    public Response(String type, String body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }
}
