package com.orchestration.instantcard.models.generals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.instantcard.exception.models.ErrorModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseModel<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorModel> errors;

}
