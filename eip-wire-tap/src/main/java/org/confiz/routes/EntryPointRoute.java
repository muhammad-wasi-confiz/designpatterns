package org.confiz.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ThreadPoolBuilder;
import org.apache.camel.model.WireTapDefinition;
import org.confiz.WireTapApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class EntryPointRoute extends RouteBuilder{
    @Autowired
    private CamelContext context;

    @Override
    public void configure() throws Exception {
        from("{{entry}}")
                .routeId("EntryPoint Route")
                .log("--------Entrypoint route--------")
                .wireTap("direct:loggingRoute")
                .to("{{endpoint}}");
    }
}
