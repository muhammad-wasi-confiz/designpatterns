package org.confiz;

import org.apache.camel.CamelContext;
import org.confiz.model.Customer;
import org.confiz.model.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SplitterApplication {
    public static void main(String[] args) throws Exception {
        // Run Spring Boot application and obtain ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(SplitterApplication.class, args);

        // Get CamelContext from ApplicationContext
        CamelContext camelContext = (CamelContext) context.getBean("camelContext");

        List<Department> dept = Stream.of(new Department(1, "address", "5400", "US"),
                new Department(2, "address 2", "5400", "JAPAN"))
                .collect(Collectors.toList());

        Customer customer = new Customer(1, "Wasi", dept);
        camelContext.createProducerTemplate().sendBody("{{entry}}", customer);
    }
}
