package com.orchestration.instantcard.exception.models;

import java.util.List;

public class BusinessException extends RuntimeException{


    private final String code;
    private final String message;

    private final transient List<ErrorModel> errors;

    public BusinessException(String code, String message, List<ErrorModel> errors){
        super(message);
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public BusinessException(String code, List<ErrorModel> errors){
        super("");
        this.code = code;
        this.message = "";
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }
}
