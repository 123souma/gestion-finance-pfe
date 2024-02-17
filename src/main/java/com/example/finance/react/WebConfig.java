package com.example.finance.react;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autoriser toutes les URL
                .allowedOrigins("http://localhost:3000") // Autoriser les requêtes depuis localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Autoriser les méthodes HTTP GET, POST, PUT, DELETE
    }
}

