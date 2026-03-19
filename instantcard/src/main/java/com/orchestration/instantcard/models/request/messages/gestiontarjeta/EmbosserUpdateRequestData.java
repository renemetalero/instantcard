package com.orchestration.instantcard.models.request.messages.gestiontarjeta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orchestration.instantcard.models.generals.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmbosserUpdateRequestData {

    @JsonProperty("header")
    @NotNull(message = "0043")
    @Valid
    private Header header;

    @JsonProperty("body")
    @NotNull(message = "0043")
    @Valid
    private EmbosserUpdateRequestBody body;
}
