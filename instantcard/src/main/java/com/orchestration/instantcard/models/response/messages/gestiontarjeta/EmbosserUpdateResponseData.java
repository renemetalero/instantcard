package com.orchestration.instantcard.models.response.messages.gestiontarjeta;

import com.orchestration.instantcard.models.generals.Header;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmbosserUpdateResponseData {
    private Header header;
    private EmbosserUpdateResponseBody body;
}
