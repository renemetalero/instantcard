package com.orchestration.instantcard.models.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InstantCardResponse {
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private String message;
    private Metadata metadata;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InstantCardResponseData data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorModel> errors;

    public InstantCardResponse() {}

    public InstantCardResponse(Integer status, String message, Metadata metadata, InstantCardResponseData data) {
        this.status = status;
        this.message = message;
        this.metadata = metadata;
        this.data = data;
        this.errors = null;
    }

    public InstantCardResponse(Integer status, String message, Metadata metadata, List<ErrorModel> errors) {
        this.status = status;
        this.message = message;
        this.metadata = metadata;
        this.data = null;
        this.errors = errors;
    }
}
