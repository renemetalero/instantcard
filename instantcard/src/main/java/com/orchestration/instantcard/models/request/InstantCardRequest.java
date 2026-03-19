package com.orchestration.instantcard.models.request;

import com.orchestration.instantcard.models.generals.Metadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstantCardRequest {

    @Valid
    @ConvertGroup(from = InstantCardRequest.class, to  = Metadata.class)
    @NotNull(message = "0501")
    private Metadata metadata;

    @Valid
    @ConvertGroup(from = InstantCardRequest.class, to  = InstantCardRequestData.class)
    @NotNull(message = "0502")
    private InstantCardRequestData data;
}
