package com.voting.app.dto;

public record LoginRequestDto(String collegeId, String password, boolean isAdmin) {
}
