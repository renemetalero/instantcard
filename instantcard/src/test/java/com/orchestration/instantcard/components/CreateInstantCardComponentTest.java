package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.gestiontarjeta.EmbosserUpdateComponent;
import com.orchestration.instantcard.components.vision.CreateInstantCardComponent;
import com.orchestration.instantcard.converter.CustomerToProviderConverter;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.FieldsCardDto;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.service.cmc.CmcClientServiceImpl;
import com.orchestration.instantcard.service.cmc.CmcServiceImpl;
import com.orchestration.instantcard.service.customeroffer.CustomerOfferUpdateService;
import com.orchestration.instantcard.service.vision.FiservServiceImpl;
import com.orchestration.instantcard.service.vision.token.AuthTokenFiservSchedulerImpl;
import com.orchestration.instantcard.service.vision.token.TokenFiservServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.LoggerObjectUtil;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CreateInstantCardComponentTest {

    private CreateInstantCardComponent createInstantCardComponent;

    @InjectMocks
    private CreatePojos create;

    @Mock
    private RestTemplate restTemplate;

    @MockBean
    FiservServiceImpl<CreacionTarjetaFiservResponse,CreacionTarjetaFiservRequest> fiservService;

    @Mock
    FiservServiceImpl fiservServiceMock;
    @Mock
    CmcServiceImpl cmcService;

    @Mock
    TokenFiservServiceImpl tokenFiservService;

    @Mock
    AuthTokenFiservSchedulerImpl authTokenFiservScheduler;
    
    @Mock
    CustomerOfferUpdateService customerOfferUpdateService;
    
    @Mock
    CustomerToProviderConverter converter;

    @Mock
    CacheManager cacheManager;

    @Mock
    CmcClientServiceImpl<CmcInstantCardResponse, CmcInstantCardRequest> cmcInstantCardService;

    @Mock
    private EmbosserUpdateComponent embosserUpdateComponent;

    @BeforeEach
    void setUp() {
        this.fiservService = new FiservServiceImpl<>(restTemplate);
        this.authTokenFiservScheduler = new AuthTokenFiservSchedulerImpl(tokenFiservService, cacheManager);
        this.createInstantCardComponent = new CreateInstantCardComponent(fiservService, 
        		cmcService, tokenFiservService, authTokenFiservScheduler, embosserUpdateComponent, 
        		customerOfferUpdateService, converter);
        ReflectionTestUtils.setField(this.createInstantCardComponent, "createInstantCardFiserv", "http://192.168.1.10/account/v2/instantCard");
    }

    @Test
    @DisplayName(value = "Test API CreateInstantCardComponent == createInstantCard")
    void testCreateInstantCardComponent() {

        when(this.tokenFiservService.getAuthToken()).thenReturn("token");

        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = create.createCreacionTarjetaFiservResponse();
        ResponseEntity<CreacionTarjetaFiservResponse> responseCreacionTarjetaFiservEntity = new ResponseEntity<>(creacionTarjetaFiservResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                        Mockito.anyString(),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.any(HttpEntity.class),
                        Mockito.eq(CreacionTarjetaFiservResponse.class)))
                .thenReturn(responseCreacionTarjetaFiservEntity);

        when(this.cmcService.insertInstantCardInformation(any(InstantCardRequest.class), any(CreacionTarjetaFiservResponseData.class)))
                .thenReturn(create.createCmcInstantCardResponse());

        ReflectionTestUtils.setField(this.fiservService, "grantType", "GrantTypee");
        ReflectionTestUtils.setField(this.fiservService, "password", "passwordd");
        ReflectionTestUtils.setField(this.fiservService, "apiKey", "apiKeyy");
        ReflectionTestUtils.setField(this.fiservService, "urlFirservLogin", "urlFirservLoginn");

        this.createInstantCardComponent.createInstantCard(create.createInstantCard(), create.createFieldsCardDto());

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(HttpEntity.class),
                Mockito.eq(CreacionTarjetaFiservResponse.class));
    }

    @Test
    @DisplayName(value = "Test API CreateInstantCardComponent == createInstantCard")
    void testExceptionCreateInstantCardComponent() {

        this.createInstantCardComponent = new CreateInstantCardComponent(fiservServiceMock, cmcService, tokenFiservService, authTokenFiservScheduler, embosserUpdateComponent, customerOfferUpdateService, converter);

        when(this.tokenFiservService.getAuthToken()).thenReturn("token");

        when(this.fiservServiceMock.consumeFiservService(any(CreacionTarjetaFiservRequest.class), any(HttpMethod.class), any(Class.class), anyBoolean()))
                .thenReturn(new CreacionTarjetaFiservResponse());

        InstantCardRequest instantCardRequest = create.createInstantCard();
        FieldsCardDto fieldsCardDto = create.createFieldsCardDto();

        assertThrows(BusinessException.class, () -> this.createInstantCardComponent.createInstantCard(instantCardRequest, fieldsCardDto));

    }



    @Test
    @DisplayName(value = "Test API CreateInstantCardComponent with error Fiserv == createInstantCard")
    void testCreateInstantCardComponentWithErrorFiserv() {

        when(this.tokenFiservService.getAuthToken()).thenReturn("token");

        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = create.createCreacionTarjetaFiservResponseWithErrors();
        ResponseEntity<CreacionTarjetaFiservResponse> responseCreacionTarjetaFiservEntity = new ResponseEntity<>(creacionTarjetaFiservResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(HttpEntity.class),
                Mockito.eq(CreacionTarjetaFiservResponse.class)))
                .thenReturn(responseCreacionTarjetaFiservEntity);

        ReflectionTestUtils.setField(this.fiservService, "grantType", "GrantTypee");
        ReflectionTestUtils.setField(this.fiservService, "password", "passwordd");
        ReflectionTestUtils.setField(this.fiservService, "apiKey", "apiKeyy");
        ReflectionTestUtils.setField(this.fiservService, "urlFirservLogin", "urlFirservLoginn");

        InstantCardRequest instantCardRequest = create.createInstantCard();
        FieldsCardDto fieldsCardDto = create.createFieldsCardDto();

        assertThrows(BusinessException.class, () -> this.createInstantCardComponent.createInstantCard(instantCardRequest,fieldsCardDto));
    }

    @Test
    @DisplayName(value = "Test API with Errors CreateInstantCardComponent == createInstantCard")
    void testCreateInstantWithErrorsCardComponent() {

        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = create.createCreacionTarjetaFiservResponse();
        ResponseEntity<CreacionTarjetaFiservResponse> responseCreacionTarjetaFiservEntity = new ResponseEntity<>(creacionTarjetaFiservResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(HttpEntity.class),
                Mockito.eq(CreacionTarjetaFiservResponse.class)))
                .thenReturn(responseCreacionTarjetaFiservEntity);

        when(this.cmcService.insertInstantCardInformation(any(InstantCardRequest.class), any(CreacionTarjetaFiservResponseData.class)))
                .thenReturn(create.createCmcInstantCardResponseWithError());

        when(this.tokenFiservService.getAuthToken()).thenReturn("token");

        ReflectionTestUtils.setField(this.fiservService, "grantType", "GrantTypee");
        ReflectionTestUtils.setField(this.fiservService, "password", "passwordd");
        ReflectionTestUtils.setField(this.fiservService, "apiKey", "apiKeyy");
        ReflectionTestUtils.setField(this.fiservService, "urlFirservLogin", "urlFirservLoginn");

        InstantCardRequest instantCardRequest = create.createInstantCard();
        FieldsCardDto fieldsCardDto = create.createFieldsCardDto();

        BusinessException thrownException = assertThrows(BusinessException.class, () -> this.createInstantCardComponent.createInstantCard(instantCardRequest, fieldsCardDto));
        assertEquals(InstantCardConstants.INSTANT_CARD_ERROR_CMC, thrownException.getCode());

    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName(value = "Test API with Errors in CMC Service CreateInstantCardComponent == createInstantCard")
    void testCreateInstantWithErrorsCMCServiceCardComponent(boolean isBusinessException) {

        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = create.createCreacionTarjetaFiservResponse();
        ResponseEntity<CreacionTarjetaFiservResponse> responseCreacionTarjetaFiservEntity = new ResponseEntity<>(creacionTarjetaFiservResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(HttpEntity.class),
                Mockito.eq(CreacionTarjetaFiservResponse.class)))
                .thenReturn(responseCreacionTarjetaFiservEntity);

        when(this.tokenFiservService.getAuthToken()).thenReturn("token");

        CmcInstantCardResponse cmcInstantCardResponse = isBusinessException ? new CmcInstantCardResponse() : create.createCmcInstantCardResponse();
        when(this.cmcService.insertInstantCardInformation(any(InstantCardRequest.class), any(CreacionTarjetaFiservResponseData.class)))
                .thenReturn(cmcInstantCardResponse);

        ReflectionTestUtils.setField(this.fiservService, "grantType", "GrantTypee");
        ReflectionTestUtils.setField(this.fiservService, "password", "passwordd");
        ReflectionTestUtils.setField(this.fiservService, "apiKey", "apiKeyy");
        ReflectionTestUtils.setField(this.fiservService, "urlFirservLogin", "urlFirservLoginn");

        if(isBusinessException) {
            InstantCardRequest instantCardRequest = create.createInstantCard();
            FieldsCardDto fieldsCardDto = create.createFieldsCardDto();
            BusinessException thrownException = assertThrows(BusinessException.class, () -> this.createInstantCardComponent.createInstantCard(instantCardRequest, fieldsCardDto));
            assertEquals(InstantCardConstants.INSTANT_CARD_ERROR_CMC, thrownException.getCode());
        }else{
            this.createInstantCardComponent.createInstantCard(create.createInstantCard(), create.createFieldsCardDto());
            verify(this.cmcService, times(1)).insertInstantCardInformation(any(InstantCardRequest.class), any(CreacionTarjetaFiservResponseData.class));
        }

    }

    @Test
    @DisplayName(value = "Test API Token Fiserv null")
    void testCreateInstantWithTokenNull() {
        try (MockedStatic<LoggerObjectUtil> mockedLogger = Mockito.mockStatic(LoggerObjectUtil.class)) {
            mockedLogger.when(() -> LoggerObjectUtil.print(Mockito.anyString(), Mockito.any()))
                    .thenAnswer(invocation -> null);
            authTokenFiservScheduler = mock(AuthTokenFiservSchedulerImpl.class);
            Mockito.when(tokenFiservService.getAuthToken()).thenReturn(null);
            InstantCardRequest instantCardRequest = create.createInstantCard();
            FieldsCardDto fieldsCardDto = create.createFieldsCardDto();
            Assertions.assertThrows(BusinessException.class, () -> this.createInstantCardComponent.createInstantCard(instantCardRequest,fieldsCardDto));
        }
    }

}
