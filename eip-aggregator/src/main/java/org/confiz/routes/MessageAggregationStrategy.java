package org.confiz.routes;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MessageAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        String in1 = (String) oldExchange.getIn().getBody();
        String in2 = (String) newExchange.getIn().getBody();

        // Doing very fancy stuff here :P
        oldExchange.getIn().setBody(in1 + "-" + in2);

        return oldExchange;
    }
}
