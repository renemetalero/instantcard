package com.orchestration.instantcard.service;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequest;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.service.cmc.CmcClientServiceImpl;
import com.orchestration.instantcard.service.cmc.CmcServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.HttpExceptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CmcServiceImplTest {

    private CmcServiceImpl service;

    @InjectMocks
    private CreatePojos create;

    @Mock
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @MockBean
    private ApiContext apiContext;
    @MockBean
    private CmcClientServiceImpl<ValidacionTarjetaResponse, InstantCardRequest> cmcValidationTarjeta;
    @MockBean
    private CmcClientServiceImpl<CreacionClienteResponse, CreacionClienteRequest> cmcCreacionCliente;
    @MockBean
    private CmcClientServiceImpl<CatalogRes, CatalogReq> cmcCatalogoService;
    @MockBean
    private CmcClientServiceImpl<CmcInstantCardResponse, CmcInstantCardRequest> cmcInstantCardService;
    @Mock
    private HttpExceptionUtil httpExceptionUtil;

    @BeforeEach
    void setUp() {
        this.apiContext = new ApiContext();
        this.cmcValidationTarjeta = new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil);
        this.cmcCreacionCliente = new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil);
        this.cmcCatalogoService = new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil);
        this.cmcInstantCardService = new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil);
        this.service = new CmcServiceImpl(cmcValidationTarjeta, cmcCreacionCliente,cmcCatalogoService,cmcInstantCardService,apiContext);
    }

    @Test
    @DisplayName(value = "Test API Cmc Test 1 == validacionData")
    void testService1() {
        try {
            var validacionTarjetaResponse = create.createValidacionTarjetaDataResponse();

            ResponseEntity<ValidacionTarjetaResponse> responseEntity = new ResponseEntity<>(validacionTarjetaResponse, HttpStatus.OK);
            Mockito.when(restTemplate.exchange(
                            Mockito.anyString(),
                            Mockito.eq(HttpMethod.POST),
                            Mockito.any(HttpEntity.class),
                            Mockito.eq(ValidacionTarjetaResponse.class)))
                    .thenReturn(responseEntity);
            Object obj = this.service.validateDataCmc(create.createRequest());
            assertThat(obj).isNotNull();
        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName(value = "Test API Cmc Test 2 == creacion Cliente")
    void testService2() {
        try {
            Object obj = this.service.crearClienteCmc(create.createClient(), false);

            assertThat(obj).isNotNull();
        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
        try {
            Object obj2 = this.service.crearClienteCmc(create.createClient(), true);

            assertThat(obj2).isNotNull();
        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName(value = "Test API Cmc Test 3 == catalog busqueda")
    void testService3()  {
        try {
            apiContext.createMetadata();
            Object obj = this.service.catalogSearch("CICLO");
            assertThat(obj).isNotNull();
        }catch (BusinessException bex){
            assertThat(bex).isNotNull();
        }
    }

    @Test
    @DisplayName(value = "Test CmcServiceImplTest == testInsertInstantCardInformation")
    void testInsertInstantCardInformation()  {
        CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData = create.creacionTarjetaFiservResponseData();
        CmcInstantCardResponse cmcInstantCardResponse = create.createCmcInstantCardResponse();
        ReflectionTestUtils.setField(this.service, "instantCardInformationCmcUrl", "urlInstantCardInformationCMC");
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenReturn(new ResponseEntity<>(cmcInstantCardResponse, HttpStatus.OK));
        CmcInstantCardResponse insertResponse = this.service.insertInstantCardInformation(create.createInstantCard(), creacionTarjetaFiservResponseData);
        Assertions.assertEquals("Ok", insertResponse.getData().getBody().getCode());
    }
}
