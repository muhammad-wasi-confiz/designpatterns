package org.confiz.chain;

import org.confiz.chain.Response;
import org.confiz.chain.handlers.JsonResponseHandler;
import org.confiz.chain.handlers.XmlResponseHandler;
import org.junit.Test;

public class ChainOfResponsibilityTest {

    @Test
    public void test() throws Exception {
        Response jsonResponse = new Response("application/json", "{'personInfo': { 'name': 'wasi', 'age': '29'}}");
        Response xmlResponse = new Response("application/xml", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<personInfo>\n" +
                "      <name>wasi</name>\n" +
                "      <age>29</age>\n" +
                "</personInfo>");
        XmlResponseHandler xmlResponseHandler = new XmlResponseHandler();
        JsonResponseHandler jsonResponseHandler = new JsonResponseHandler();

        xmlResponseHandler.setNextHandler(jsonResponseHandler);

        Object process = xmlResponseHandler.process(jsonResponse, "$.personInfo.name");
        System.out.println(process);

        Object process1 = xmlResponseHandler.process(xmlResponse, "/personInfo[name=wasi]");
        System.out.println(process1);
    }
}
