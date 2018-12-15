package com.rocky.oauth.resources_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class ResourcesServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourcesServerApplication.class, args);
    }

}

