package com.qa.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    // @Scope("singleton")   // Singleton by default
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}