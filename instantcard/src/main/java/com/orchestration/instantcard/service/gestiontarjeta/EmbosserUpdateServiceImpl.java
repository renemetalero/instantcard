package com.orchestration.instantcard.service.gestiontarjeta;

import java.time.Duration;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestData;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponse;
import com.orchestration.instantcard.utils.LoggerObjectUtil;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import com.orchestration.instantcard.utils.constants.LogLevelConstants;

import lombok.Setter;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class EmbosserUpdateServiceImpl implements EmbosserUpdateService {

    @Setter
    private String resourceUrlEmbosserUpdate;

    private final WebClient webClient;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public EmbosserUpdateServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<EmbosserUpdateResponse> updateEmail(EmbosserUpdateRequest embosserUpdateRequest) {

        logger.info("resourceUrlFiserv {}", resourceUrlEmbosserUpdate);
        LoggerObjectUtil.print("Request updateEmail", embosserUpdateRequest);

        Optional<EmbosserUpdateRequest> embosserUpdateRequestOptional =Optional.ofNullable(embosserUpdateRequest);

        String cardNumber = embosserUpdateRequestOptional
                .map(EmbosserUpdateRequest::getData)
                .map(EmbosserUpdateRequestData::getBody)
                .map(EmbosserUpdateRequestBody::getCardNumber)
                .orElse(InstantCardConstants.CARD_NOT_FOUND);

        EmbosserUpdateRequest embosserUpdateRequestParam = embosserUpdateRequestOptional.orElse(new EmbosserUpdateRequest());

        return webClient.post()
                .uri(this.resourceUrlEmbosserUpdate)
                .bodyValue(embosserUpdateRequestParam)
                .retrieve()
                .bodyToMono(EmbosserUpdateResponse.class)
                .retryWhen(Retry.fixedDelay(CommonConstants.RETRY_ATTEMPT_COUNT, Duration.ofSeconds(CommonConstants.RETRY_ATTEMPT_DELAY))
                        .doBeforeRetry(signal ->
                                logger.info("Reintento #{} debido a {}", signal.totalRetries(), signal.failure().getMessage())))
                .onErrorResume(error -> {
                    logger.error("[{}] Todos los intentos updateEmail cardNumber {} fallaron: {}", LogLevelConstants.CRITICAL.getText(), cardNumber,error.getMessage());
                    return Mono.just(new EmbosserUpdateResponse());
                });
    }

}
