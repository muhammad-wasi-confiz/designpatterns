package org.confiz.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoadBalancerRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:loadBalancerRoute1")
                .log("loadBalancer Route1 -> ${body}");
        from("direct:loadBalancerRoute2")
                .log("loadBalancer Route2 -> ${body}");
        from("direct:loadBalancerRoute3")
                .log("loadBalancer Route3 -> ${body}");
    }
}
