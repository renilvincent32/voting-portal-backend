package com.voting.app.exception;

public class VoterNotFoundException extends RuntimeException {

    public VoterNotFoundException(String collegeId) {
        super(String.format("Voter with collegeId %s not found", collegeId));
    }
}
