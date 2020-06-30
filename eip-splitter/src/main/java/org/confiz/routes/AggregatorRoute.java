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
        from("{{endpoint.aggregator}}").routeId("Aggregator Route")
                .aggregate(constant(true), aggregator)
                .completionSize(3).completionInterval(2000)
                .to("{{endpoint}}");
    }
}
