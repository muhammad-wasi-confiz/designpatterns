package org.confiz.chain.handlers;

import org.confiz.chain.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class XmlResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;

    @Override
    public void setNextHandler(ResponseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public Object process(Response response, String query) throws Exception {
        boolean compatible = "application/xml".equalsIgnoreCase(response.getType());

        if (!compatible && nextHandler != null) {
            return nextHandler.process(response, query);
        }

        if (response.getBody() != null) {

            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.getBody())));

            XPathExpression expr = XPathFactory.newInstance()
                    .newXPath().compile(query);
            NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            if (nodeList.getLength() > 0) {
                Node item = nodeList.item(0);
                if (item != null && item.hasChildNodes()) {
                    Node firstChild = item.getFirstChild();
                    return firstChild.getNodeValue();
                }
            }
        }


        return null;
    }
}
