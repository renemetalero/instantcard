package com.orchestration.instantcard.models.response.messages.gestiontarjeta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmbosserUpdateResponse {
    private Metadata metadata;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EmbosserUpdateResponseData data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorModel> errors;
}
