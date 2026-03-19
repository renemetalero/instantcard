package com.orchestration.instantcard.models.request.messages.cmc.createcard;


import com.orchestration.instantcard.models.generals.Metadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CmcInstantCardRequest {
    @NotNull(message = "0021")
    @Valid
    private Metadata metadata;

    @NotNull(message = "0004")
    @Valid
    private CmcInstantCardRequestData data;
}
