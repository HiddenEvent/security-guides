package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @GetMapping("/contact")
    public String getContact() {
        return "연락처 정보를 DB에 접근하여 가져오기";
    }
}
