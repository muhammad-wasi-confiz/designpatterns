package org.confiz;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Random;

@SpringBootApplication
public class WireTapApplication {
    public static void main(String[] args) throws Exception {
        // Run Spring Boot application and obtain ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(WireTapApplication.class, args);

        // Get CamelContext from ApplicationContext
        CamelContext camelContext = (CamelContext) context.getBean("camelContext");

        Random r = new Random();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        for (int i = 0; i < 20; i++) {
            int randomNumber = r.nextInt();
            producerTemplate.sendBody("{{entry}}", randomNumber);
        }
    }
}
