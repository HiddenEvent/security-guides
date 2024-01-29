package me.ricky.guides.securityguides.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    SYSTEM("시스템"),
    ADMIN("관리자"),
    USER("사용자");


    private final String title;

    public static UserRole of(String title) {
        return Arrays.stream(values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new RuntimeException(title));
    }

    public String getCode() {
        return name();
    }
}