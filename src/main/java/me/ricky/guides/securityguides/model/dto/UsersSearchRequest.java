package me.ricky.guides.securityguides.model.dto;

public record UsersSearchRequest(String username, String firstName, String lastName, String email) {
}
