package org.confiz.routes;

import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainEndpointRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("{{endpoint}}")
                .log("ENDPOINT: ${body}");
    }
}
