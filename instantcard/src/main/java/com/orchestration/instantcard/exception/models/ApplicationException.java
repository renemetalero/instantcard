package com.orchestration.instantcard.exception.models;

import java.util.List;

public class ApplicationException extends RuntimeException{

    private final String message;

    private final transient List<ErrorModel> errors;

    public ApplicationException(String message, List<ErrorModel> errors){
        super(message);
        this.message = message;
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }
}