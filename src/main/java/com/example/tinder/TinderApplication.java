package com.example.tinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TinderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinderApplication.class, args);
    }

}
