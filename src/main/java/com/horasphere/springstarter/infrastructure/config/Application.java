package com.horasphere.springstarter.infrastructure.config;

import com.horasphere.springstarter.interfaces.rest.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    HomeController homeController() {
        return new HomeController();
    }
}
