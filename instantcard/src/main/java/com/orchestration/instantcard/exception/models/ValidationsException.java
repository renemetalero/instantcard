package com.orchestration.instantcard.exception.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationsException extends RuntimeException {

    private final String title;
    private final transient List<ErrorModel> errorsModel;

    public ValidationsException(List<ErrorModel> errors){
        super("");
        this.title = "";
        this.errorsModel = errors;
    }
    public ValidationsException(List<ErrorModel> errors, String title){
        super(title);
        this.title = title;
        this.errorsModel = errors;
    }
    public ValidationsException(ErrorModel error){
        super("");
        List<ErrorModel> errors = new ArrayList<>();
        errors.add(error);
        this.title = "";
        this.errorsModel = errors;
    }

}
