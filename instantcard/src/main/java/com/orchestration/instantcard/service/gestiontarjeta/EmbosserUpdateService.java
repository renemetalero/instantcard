package com.orchestration.instantcard.service.gestiontarjeta;

import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponse;
import reactor.core.publisher.Mono;

public interface EmbosserUpdateService {
    Mono<EmbosserUpdateResponse> updateEmail(EmbosserUpdateRequest embosserUpdateRequest);
    void setResourceUrlEmbosserUpdate(String resourceUrlCustomerofferUpdate);
}
