package com.orchestration.instantcard.models.request.messages.cmc.createcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmcInstantCardRequestData {
    @JsonProperty("body")
    @NotNull(message = "0043")
    @Valid
    private CmcInstantCardRequestBody body;
}
