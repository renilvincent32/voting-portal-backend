package com.voting.app.dto;

public record VoterDto(
        String collegeId,
        String firstName,
        String lastName,
        String branch,
        boolean isAdmin) {
}
