package com.orchestration.instantcard.models.request.messages.gestiontarjeta;

import com.orchestration.instantcard.models.generals.Metadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmbosserUpdateRequest {
    @NotNull(message = "0021")
    @Valid
    private Metadata metadata;

    @NotNull(message = "0004")
    @Valid
    private EmbosserUpdateRequestData data;
}
