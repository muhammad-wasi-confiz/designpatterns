package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggregatorRoute extends RouteBuilder {

    @Autowired
    private MessageAggregationStrategy aggregator;

    @Override
    public void configure() throws Exception {
        from("{{entry}}")
                .log("Body -> ${body} and CorrelationId -> ${header.correlationId}")
                .aggregate(header("correlationId"), aggregator)
                .completionSize(3).completionInterval(2000)
                .to("{{endpoint}}");
    }
}
