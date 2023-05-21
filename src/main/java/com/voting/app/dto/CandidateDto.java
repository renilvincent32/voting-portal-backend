package com.voting.app.dto;

public record CandidateDto(int id,
                           String firstName,
                           String lastName,
                           String branch,
                           byte[] imgData,
                           String symbol,
                           String campaignQuote,
                           String designation) {
}
