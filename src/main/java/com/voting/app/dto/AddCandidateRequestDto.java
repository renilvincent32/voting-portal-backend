package com.voting.app.dto;

public record AddCandidateRequestDto(String firstName,
                                     String lastName,
                                     String branch,
                                     String symbol,
                                     String imgPath,
                                     String campaignQuote,
                                     String designation) {
}
