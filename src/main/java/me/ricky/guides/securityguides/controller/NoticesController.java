package me.ricky.guides.securityguides.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
    @GetMapping("/notices")
    public String getNotices() {
        return "공지사항을 DB에 접근하여 가져오기";
    }

}
