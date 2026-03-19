package com.orchestration.instantcard.service;

import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponse;
import com.orchestration.instantcard.service.gestiontarjeta.EmbosserUpdateServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.TestsConstants;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.logging.Logger;

@ExtendWith(MockitoExtension.class)
class EmbosserUpdateServiceImplTest {

    @InjectMocks
    private EmbosserUpdateServiceImpl embosserUpdateService;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private CreatePojos create;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        this.embosserUpdateService = new EmbosserUpdateServiceImpl(webClient);
        this.embosserUpdateService.setResourceUrlEmbosserUpdate(TestsConstants.EMBOSSER_UPDATE_URL_TEST);
    }

    @Test
    @DisplayName("testEmbosserUpdateEmail -- testValidateOk")
    void testEmbosserUpdateEmailOk(){
        EmbosserUpdateRequest request = create.createEmbosserUpdateRequest();
        EmbosserUpdateResponse response = create.createEmbosserUpdateResponse();

        Mockito.when(webClient.post()).thenReturn(requestBodyUriSpec);
        Mockito.when(requestBodyUriSpec.uri(Mockito.anyString())).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.bodyValue(Mockito.any())).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToMono(EmbosserUpdateResponse.class)).thenReturn(Mono.just(response));

        Mono<EmbosserUpdateResponse> result = embosserUpdateService.updateEmail(request);

        Assertions.assertNotNull(result);
        result.subscribe(actualResponse -> Assertions.assertNotNull(actualResponse));

        Mockito.verify(webClient, Mockito.times(1)).post();
        Mockito.verify(requestBodyUriSpec, Mockito.times(1)).uri(TestsConstants.EMBOSSER_UPDATE_URL_TEST);
        Mockito.verify(requestBodySpec, Mockito.times(1)).bodyValue(request);
        Mockito.verify(requestHeadersSpec, Mockito.times(1)).retrieve();
        Mockito.verify(responseSpec, Mockito.times(1)).bodyToMono(EmbosserUpdateResponse.class);
    }

    @Test
    @DisplayName("testEmbosserUpdateEmail -- testValidateError")
    void testEmbosserUpdateEmailError(){

        EmbosserUpdateRequest request = new EmbosserUpdateRequest();

        Mockito.when(webClient.post()).thenReturn(requestBodyUriSpec);
        Mockito.when(requestBodyUriSpec.uri(Mockito.anyString())).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.bodyValue(Mockito.any())).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        Mockito.when(responseSpec.bodyToMono(EmbosserUpdateResponse.class))
                .thenReturn(Mono.error(new RuntimeException("Simulated API failure")));

        Mono<EmbosserUpdateResponse> result = embosserUpdateService.updateEmail(request);

        StepVerifier.create(result)
                .expectNextMatches(response -> response != null && response instanceof EmbosserUpdateResponse) // Se retorna un valor alternativo tras los reintentos
                .verifyComplete();

        Mockito.verify(webClient, Mockito.times(1)).post();

    }



}
