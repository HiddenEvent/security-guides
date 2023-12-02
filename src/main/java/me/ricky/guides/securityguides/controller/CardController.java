package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/myCard")
    public String getCardDetails() {
        return "내 카드 정보를 DB에 접근하여 가져오기";
    }
}
