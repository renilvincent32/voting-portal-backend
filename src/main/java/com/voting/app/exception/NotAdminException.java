package com.voting.app.exception;

public class NotAdminException extends RuntimeException{

    public NotAdminException(String collegeId) {
        super(String.format("Voter with collegeId %s is not admin", collegeId));
    }
}
