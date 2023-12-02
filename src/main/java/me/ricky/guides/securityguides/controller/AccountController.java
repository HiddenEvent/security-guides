package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "내 계정 상세 정보를 DB에 접근하여 가져오기";
    }
}
