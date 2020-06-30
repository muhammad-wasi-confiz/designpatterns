package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EntryPointRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("{{entry}}")
                .loadBalance()
                .roundRobin()
                .to("direct:loadBalancerRoute1")
                .to("direct:loadBalancerRoute2")
                .to("direct:loadBalancerRoute3")
                .end();


    }
}
