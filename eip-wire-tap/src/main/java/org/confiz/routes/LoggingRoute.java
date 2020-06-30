package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoggingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:loggingRoute")
                .routeId("Logging Route")
                .log("Logging Route -> ${body}");
    }
}
