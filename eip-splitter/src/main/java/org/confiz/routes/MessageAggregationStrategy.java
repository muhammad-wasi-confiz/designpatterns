package org.confiz.routes;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.confiz.model.Department;
import org.springframework.stereotype.Component;

@Component
public class MessageAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        Department in1 = (Department) oldExchange.getIn().getBody();
        Department in2 = (Department) newExchange.getIn().getBody();

        // Doing very fancy stuff here :P
        oldExchange.getIn().setBody(in1.toString() + "-" + in2.toString());

        return oldExchange;
    }
}
