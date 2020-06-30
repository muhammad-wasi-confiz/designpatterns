package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EndpointRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("{{endpoint}}").routeId("EndPoint Route")
                .log("Department -> ${body.toString}");
    }
}
