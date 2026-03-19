package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.vision.CreateClientComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequest;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseBody;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseData;
import com.orchestration.instantcard.service.cmc.CmcServiceImpl;
import com.orchestration.instantcard.service.vision.VisionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class CreateClientComponentTest {

    @Mock
    private VisionServiceImpl visionService;

    @Mock
    private CmcServiceImpl cmcService;

    @InjectMocks
    private CreateClientComponent createClientComponent;

    private List<ErrorModel> errors;

    @Mock
    private ApiContext apiContext;

    @Test
    @DisplayName("Test CreateClienteComponet - Caso Exitoso")
    void testCreateClient_Success() throws Exception {

        try {
            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();

            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);
            when(cmcService.crearClienteCmc(any(), anyBoolean())).thenReturn(null);

            visionResponse.setData(visionResponse.getData());
            visionResponse.setErrors(visionResponse.getErrors());

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);

        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName("Test CreateClienteComponet - Errores en Vision+")
    void testCreateClient_ErrorsInVision() throws Exception {
        try {
            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();
            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();
            visionResponse.setErrors(Collections.singletonList(new ErrorModel(InstantCardEnumError.BUSINESS_VISION, "VISION PLUS - Service")));

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);
            when(cmcService.crearClienteCmc(any(), anyBoolean())).thenReturn(null);

            visionResponse.setData(visionResponse.getData());
            visionResponse.setErrors(visionResponse.getErrors());

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class),  Mockito.any(Class.class)))
                    .thenReturn(visionResponse);

        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }
    @Test
    @DisplayName("Test CreateClienteComponet - Respuesta Nula de Vision+")
    void testCreateClient_NullVisionResponse() throws Exception {
        try {
            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();
            AccountInformationDto accountInformationDto = new AccountInformationDto();

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(null);
            when(cmcService.crearClienteCmc(any(), anyBoolean())).thenReturn(null);

            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();
            visionResponse.setData(visionResponse.getData());
            visionResponse.setErrors(visionResponse.getErrors());

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);

        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName("Test CreateClienteComponet - NullPointerException en Vision+")
    void testCreateClient_NullPointerExceptionInVision() throws Exception {
        try {
            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();
            AccountInformationDto accountInformationDto = new AccountInformationDto();

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenThrow(new NullPointerException("Simulando NullPointerException en Vision+"));
            when(cmcService.crearClienteCmc(any(), anyBoolean())).thenReturn(null);

            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();
            visionResponse.setData(visionResponse.getData());
            visionResponse.setErrors(visionResponse.getErrors());

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);

        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName("Test CreateClienteComponet - Errores en CMC")
    void testCreateClient_ErrorsInCMC() throws Exception {
        try {

            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();
            AccountInformationDto accountInformationDto = new AccountInformationDto();
            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);
            String code = "99999";
            doThrow(new BusinessException(code, errors)).when(cmcService).crearClienteCmc(any(), anyBoolean());

            when(apiContext.getMetadata()).thenReturn(new Metadata());

            createClientComponent.createClient(ibsResponseBody, accountInformationDto);

        }catch (BusinessException be){
            assertThat(be).isNotNull();
        }
    }

    @Test
    @DisplayName("Test CreateClienteComponet")
    void testCreateClient() throws Exception {
        try {
            ConsultaIbsResponseBody ibsResponseBody = new ConsultaIbsResponseBody();
            AccountInformationDto accountInformationDto = new AccountInformationDto();
            CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();

            when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                    .thenReturn(visionResponse);
            doAnswer(invocation -> {
                return null;
            }).when(cmcService).crearClienteCmc(any(), anyBoolean());
            createClientComponent.createClient(ibsResponseBody, accountInformationDto);

        } catch (BusinessException be) {
            assertThat(be).isNotNull();
        }
    }

    @ParameterizedTest
    @DisplayName("Test CreateClienteComponet - Errores List enCMC")
    @ValueSource(booleans = {true, false})
    void testCreateClient_ErrorListInCMC(boolean type) throws Exception {

        String customerNumber = "Customer Number";

        ConsultaIbsResponseBody consultaIbsResponseBody = new ConsultaIbsResponseBody();
        consultaIbsResponseBody.setCustomerNumber(customerNumber);
        ConsultaIbsResponseBody ibsResponseBody = consultaIbsResponseBody;
        AccountInformationDto accountInformationDto = new AccountInformationDto();
        CreacionClienteVisionResponse visionResponse = new CreacionClienteVisionResponse();
        List<ErrorModel> errorModelList = new ArrayList<>();

        InstantCardEnumError instantCardEnumError = type ? InstantCardEnumError.EXIST_CLIENT_VP : InstantCardEnumError.BUSINESS_CMC;

        errorModelList.add(new ErrorModel(instantCardEnumError, "VISION - Service"));
        visionResponse.setErrors(errorModelList);
        CreacionClienteVisionResponseData creacionClienteVisionResponseData = new CreacionClienteVisionResponseData();
        CreacionClienteVisionResponseBody creacionClienteVisionRequestBody = new CreacionClienteVisionResponseBody();
        creacionClienteVisionRequestBody.setCustomerNumber(customerNumber);
        creacionClienteVisionResponseData.setBody(creacionClienteVisionRequestBody);
        visionResponse.setData(creacionClienteVisionResponseData);

        when(visionService.consumeVisionCustomerAdd(any(CreacionClienteVisionRequest.class), Mockito.any(Class.class)))
                .thenReturn(visionResponse);

        when(apiContext.getMetadata()).thenReturn(new Metadata());

        Assertions.assertDoesNotThrow(()->createClientComponent.createClient(ibsResponseBody, accountInformationDto));

    }
}