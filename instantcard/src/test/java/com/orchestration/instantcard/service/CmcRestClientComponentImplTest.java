package com.orchestration.instantcard.service;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.service.cmc.CmcClientServiceImpl;
import com.orchestration.instantcard.utils.ResponseExceptionMockModel;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CmcRestClientComponentImplTest {
    private CmcClientServiceImpl<CatalogRes, CatalogReq> cmcCatalogoService;
    private CmcClientServiceImpl<ResponseExceptionMockModel, Object> cmcCatalogoServiceWithError;

    @Mock
    RestTemplate restTemplate;
    @Mock
    HttpExceptionUtil httpExceptionUtil;

    @Mock
    CatalogReq catalogReq;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.cmcCatalogoServiceWithError = new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil);
        this.cmcCatalogoService = spy(new CmcClientServiceImpl<>(restTemplate, httpExceptionUtil));
    }

    @Test
    @DisplayName("CmcClientServiceImpl -- testValidateError")
    void testValidateError(){
        CatalogReq catalogReq = create.createCatalogRequest();
        Optional<CreacionClienteResponse> creacionClienteResponse = Optional.ofNullable(create.creacionClienteResponseWithError());
        Mockito.when(httpExceptionUtil.checkException(Mockito.any(Exception.class), Mockito.any(Class.class))).thenReturn(creacionClienteResponse);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenThrow(new RuntimeException("Error"));
        cmcCatalogoService.comsumeCmcService(catalogReq, HttpMethod.GET, CatalogRes.class,true);
        Mockito.verify(httpExceptionUtil, Mockito.times(1)).checkException(Mockito.any(Exception.class), Mockito.any(Class.class));
    }

    @Test
    @DisplayName("CmcClientServiceImpl -- testErrorClassInstancdError")
    void testErrorClassInstancdError(){
        Class<ResponseExceptionMockModel> responseClass = ResponseExceptionMockModel.class;
        Object object = new Object();
        BusinessException thrownException = assertThrows(BusinessException.class,
                () -> cmcCatalogoServiceWithError.comsumeCmcService(object, HttpMethod.POST, responseClass, false));
        assertEquals(thrownException.getCode(), InstantCardEnumError.BUSINESS_CMC.getCode());
    }

    @Test
    @DisplayName(value = "Test cmcServiceExceptionInServiceTest ==> testConstructor <===")
    void cmcServiceExceptionInServiceTest() {
        CatalogRes catalogRes = cmcCatalogoService.comsumeCmcService(catalogReq, HttpMethod.POST, CatalogRes.class, false);
        cmcCatalogoService.getResourceUrlCmc();
        Assertions.assertNull(catalogRes.getData());
    }

    @Test
    @DisplayName(value = "Test cmcServiceExceptionTrueInServiceTest ==> testConstructor <===")
    void cmcServiceExceptionTrueInServiceTest() {

        CreacionClienteResponse creacionClienteResponse = new CreacionClienteResponse();
        creacionClienteResponse.setErrors(List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_CMC.getCode(),
                InstantCardEnumError.BUSINESS_CMC.getTitle(),
                InstantCardEnumError.BUSINESS_CMC.getMessage())));
        when(httpExceptionUtil.checkException(any(Exception.class), any(Class.class)))
                .thenReturn(Optional.of(creacionClienteResponse));

        BusinessException thrownException = assertThrows(BusinessException.class,
                () -> cmcCatalogoService.comsumeCmcService(catalogReq, HttpMethod.POST, CatalogRes.class, true));
        assertEquals(thrownException.getCode(), InstantCardEnumError.BUSINESS_CMC.getCode());

    }
}
