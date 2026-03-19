package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.gestiontarjeta.EmbosserUpdateComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.service.gestiontarjeta.EmbosserUpdateServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class EmbosserUpdateComponentTest {

    @InjectMocks
    private EmbosserUpdateComponent embosserUpdateComponent;

    @Mock
    private EmbosserUpdateServiceImpl embosserUpdateService;

    @MockBean
    private ApiContext apiContext;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.apiContext = new ApiContext();
        this.embosserUpdateComponent = new EmbosserUpdateComponent(embosserUpdateService,apiContext);
    }

    @Test
    @DisplayName("Test EmbosserUpdateComponent ==> testEmbosserUpdateOk <===")
    void testEmbosserUpdateOk() {
        InstantCardRequest instantCardRequest = create.createInstantCard();
        CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData = create.createCreacionTarjetaFiservResponse().getData();
        EmbosserUpdateResponse embosserUpdateResponse = create.createEmbosserUpdateResponse();

        ReflectionTestUtils.setField(this.embosserUpdateComponent, "embosserUpdateUrl", "urlEmbosserUpdate");

        Mockito.when(embosserUpdateService.updateEmail(Mockito.any()))
                .thenReturn(Mono.just(embosserUpdateResponse));

        this.embosserUpdateComponent.updateEmail(instantCardRequest, creacionTarjetaFiservResponseData);

        Mockito.verify(embosserUpdateService, Mockito.times(1)).setResourceUrlEmbosserUpdate(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(embosserUpdateService);

    }

}
