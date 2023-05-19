package com.voting.app.exception;

public class IncorrectUsernamePasswordException extends RuntimeException {

    public IncorrectUsernamePasswordException() {
        super("Incorrect username or password");
    }
}
