package me.ricky.guides.securityguides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SecurityGuidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityGuidesApplication.class, args);
    }

}
