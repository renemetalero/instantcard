package com.orchestration.instantcard.models.response.messages.cmc.createcard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CmcInstantCardResponse {
    private Metadata metadata;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CmcInstantCardResponseData data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorModel> errors;

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public List<ErrorModel> getErrorsList() {
        return getErrors();
    }

}
