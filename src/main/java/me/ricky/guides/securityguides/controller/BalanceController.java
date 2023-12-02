package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    @GetMapping("/myBalance")
    public String getBalance() {
        return "내 계좌 잔액을 DB에 접근하여 가져오기";
    }
}
