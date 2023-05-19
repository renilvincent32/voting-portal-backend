package com.voting.app.exception;

import com.voting.app.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class VotingAppControllerAdvice {

    @ExceptionHandler(VoterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto voterNotFoundHandler(VoterNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(DesignationNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto designationNotFoundHandler(DesignationNotFound ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto candidateNotFoundHandler(CandidateNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(NotAdminException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto voterNotAdminHandler(NotAdminException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(IncorrectUsernamePasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto incorrectUsernamePasswordHandler(IncorrectUsernamePasswordException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleDBErrors(SQLException ex) { return new ErrorDto(ex.getMessage()); }

}
