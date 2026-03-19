package com.orchestration.instantcard.service;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.RestClientWithAuthParams;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponse;
import com.orchestration.instantcard.service.vision.FiservServiceImpl;
import com.orchestration.instantcard.utils.ResponseExceptionMockModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class FiservServiceImplTest {

    private FiservServiceImpl<ResponseExceptionMockModel, Object> fiservServiceWithError;

    private FiservServiceImpl<CreacionTarjetaFiservResponse, CreacionTarjetaFiservRequest> fiservService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CreacionTarjetaFiservRequest creacionTarjetaFiservRequest;

    @BeforeEach
    void setUp() {
        this.fiservServiceWithError = new FiservServiceImpl(restTemplate);
        this.fiservService = spy(new FiservServiceImpl<>(restTemplate));
        this.fiservService.setResourceUrlFiserv("resourceUrlFiserv");
    }

    @Test
    @DisplayName(value = "Test FiservServiceImpl Error in instation ==> testConstructor <===")
    void FiservServiceErrorInInstationTest() {
        Class<ResponseExceptionMockModel> responseClass = ResponseExceptionMockModel.class;
        Object object = new Object();
        BusinessException thrownException = assertThrows(BusinessException.class,
                () -> fiservServiceWithError.consumeFiservService(object, HttpMethod.POST, responseClass, false));
        assertEquals(thrownException.getCode(), InstantCardEnumError.BUSINESS_VISION_FISERV.getCode());
    }

    @Test
    @DisplayName(value = "Test FiservServiceExceptionInServiceTest ==> testConstructor <===")
    void FiservServiceExceptionInServiceTest() {
        doThrow(new BusinessException(InstantCardEnumError.BUSINESS_VISION_FISERV.getCode(), InstantCardEnumError.BUSINESS_VISION_FISERV.getTitle(), null))
                .when(fiservService)
                .sendMessageWithAuth(Mockito.any(CreacionTarjetaFiservRequest.class), Mockito.any(), Mockito.any(RestClientWithAuthParams.class));

        BusinessException thrownException = assertThrows(BusinessException.class,
                () -> fiservService.consumeFiservService(creacionTarjetaFiservRequest, HttpMethod.POST, CreacionTarjetaFiservResponse.class, true));
        assertEquals(thrownException.getCode(), InstantCardEnumError.BUSINESS_VISION_FISERV.getCode());
    }




}
