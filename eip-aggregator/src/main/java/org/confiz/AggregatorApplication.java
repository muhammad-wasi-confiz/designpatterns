package org.confiz;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AggregatorApplication {
    public static void main(String[] args) throws Exception {
        // Run Spring Boot application and obtain ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(AggregatorApplication.class, args);

        // Get CamelContext from ApplicationContext
        CamelContext camelContext = (CamelContext) context.getBean("camelContext");

        // Add producer that will send test message to an entry point in WireTapRoute
        String[] stringArray = {"Message 1", "Message 1", "Message 3"};

        for (String message : stringArray) {
            camelContext.createProducerTemplate().sendBodyAndHeader("{{entry}}", message, "correlationId", message);
        }


    }
}
