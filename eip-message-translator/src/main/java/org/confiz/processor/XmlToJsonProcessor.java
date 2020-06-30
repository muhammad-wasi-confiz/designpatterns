package org.confiz.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class XmlToJsonProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String xml = exchange.getIn().getBody(String.class);
        JSONObject jsonObject = XML.toJSONObject(xml);
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        String jsonPrettyPrintString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);

        exchange.getIn().setBody(jsonPrettyPrintString);
    }
}
