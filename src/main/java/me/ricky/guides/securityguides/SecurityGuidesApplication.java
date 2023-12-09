package me.ricky.guides.securityguides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SecurityGuidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityGuidesApplication.class, args);
    }

}
