package com.voting.app.exception;

public class CandidateNotFoundException extends RuntimeException{

    public CandidateNotFoundException(int candidateId) {
        super(String.format("Candidate with id %d not found", candidateId));
    }
}
