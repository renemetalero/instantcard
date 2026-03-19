package com.orchestration.instantcard.models.response.messages.vision.card.fiserv;

import com.orchestration.instantcard.exception.models.ErrorModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreacionTarjetaFiservResponse {
    private Boolean hasError;
    private List<ErrorModel> errors;
    private CreacionTarjetaFiservResponseData data;
}
