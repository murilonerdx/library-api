package com.murilonerdx.restapispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@EntityScan(basePackages = {"com.murilonerdx.restapispring.model"})
@EnableJpaRepositories(basePackages= {"com.murilonerdx.restapispring.repository", "com.murilonerdx.restapispring.service"})
@EnableAutoConfiguration
public class RestApiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiSpringApplication.class, args);
    }

}
