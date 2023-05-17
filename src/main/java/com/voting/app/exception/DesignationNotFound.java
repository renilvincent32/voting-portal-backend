package com.voting.app.exception;

public class DesignationNotFound extends RuntimeException {

    public DesignationNotFound(String designationName) {
        super(String.format("Designation with name %s not found", designationName));
    }
}
