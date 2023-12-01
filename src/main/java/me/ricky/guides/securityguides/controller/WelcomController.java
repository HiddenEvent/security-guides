package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome to Spring Security Guides!";
    }
}
