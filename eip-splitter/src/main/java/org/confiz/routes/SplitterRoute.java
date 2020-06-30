package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.confiz.splitter.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{entry}}").routeId("Spliiter Route")
                .log("Body -> ${body}")
                .split().method(CustomerService.class, "splitDepartments")
                .to("{{endpoint.aggregator}}");
    }
}
