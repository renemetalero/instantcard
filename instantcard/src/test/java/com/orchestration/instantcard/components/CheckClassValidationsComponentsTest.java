package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.ChecksClassValidationsComponents;
import com.orchestration.instantcard.components.vision.CreateClientComponent;
import com.orchestration.instantcard.components.vision.CreateInstantCardComponent;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaBodyResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaDataResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseData;
import com.orchestration.instantcard.utils.Authorization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


class CheckClassValidationsComponentsTest {

    private ChecksClassValidationsComponents components;

    @Mock
    private Authorization authorizations;
    @Mock
    private CreateClientComponent createClientComponent;
    @Mock
    private CreateInstantCardComponent createInstantCardComponent;

    @BeforeEach
    void setUp() {
        components = new ChecksClassValidationsComponents(authorizations, createClientComponent, createInstantCardComponent);
    }

    @Test
    @DisplayName(value = "Test API checkClassValidationsComponents Test")
    void testClasssValidationOk() {

        ConsultaIbsResponse responseIbs = new ConsultaIbsResponse();
        ConsultaIbsResponseData data = new ConsultaIbsResponseData();
        ConsultaIbsResponseBody consultaIbsResponseBody = new ConsultaIbsResponseBody();
        consultaIbsResponseBody.setNameMagneticStripe("Ibs name Magnetic");
        consultaIbsResponseBody.setLastNameMagneticStripe("Ibs last name Magnetic");
        consultaIbsResponseBody.setShortName("Ibs short Name");
        data.setBody(consultaIbsResponseBody);
        responseIbs.setData(data);

        ValidacionTarjetaResponse cmc = new ValidacionTarjetaResponse();
        ValidacionTarjetaDataResponse cmcData = new ValidacionTarjetaDataResponse();
        ValidacionTarjetaBodyResponse validacionTarjetaBodyResponse = new ValidacionTarjetaBodyResponse();
        validacionTarjetaBodyResponse.setNameMagneticStripe("CMC name Magnetic");
        validacionTarjetaBodyResponse.setLastNameMagneticStripe("CMC last name Magnetic");
        validacionTarjetaBodyResponse.setShortName("CMC short Name");
        cmcData.setBody(validacionTarjetaBodyResponse);
        cmc.setData(cmcData);

        AccountInformationDto accountInformationDto = components.checksFullNameClient(cmc, responseIbs);

        Assertions.assertEquals(validacionTarjetaBodyResponse.getShortName(), accountInformationDto.getShortName());
        Assertions.assertEquals(validacionTarjetaBodyResponse.getNameMagneticStripe(), accountInformationDto.getName());
        Assertions.assertEquals(validacionTarjetaBodyResponse.getLastNameMagneticStripe(), accountInformationDto.getLastName());

    }

    @Test
    @DisplayName(value = "Test API testClassValidation with CMC Null Test")
    void testClassValidationWithCMCNull() {

        ConsultaIbsResponse responseIbs = new ConsultaIbsResponse();
        ConsultaIbsResponseData data = new ConsultaIbsResponseData();
        ConsultaIbsResponseBody consultaIbsResponseBody = new ConsultaIbsResponseBody();
        consultaIbsResponseBody.setNameMagneticStripe("Ibs name Magnetic");
        consultaIbsResponseBody.setLastNameMagneticStripe("Ibs last name Magnetic");
        consultaIbsResponseBody.setShortName("Ibs short Name");
        data.setBody(consultaIbsResponseBody);
        responseIbs.setData(data);

        ValidacionTarjetaResponse cmc = new ValidacionTarjetaResponse();
        ValidacionTarjetaDataResponse cmcData = new ValidacionTarjetaDataResponse();
        cmcData.setBody(null);
        cmc.setData(cmcData);

        AccountInformationDto accountInformationDto = components.checksFullNameClient(cmc, responseIbs);

        Assertions.assertEquals(consultaIbsResponseBody.getShortName(), accountInformationDto.getShortName());
        Assertions.assertEquals(consultaIbsResponseBody.getNameMagneticStripe(), accountInformationDto.getName());
        Assertions.assertEquals(consultaIbsResponseBody.getLastNameMagneticStripe(), accountInformationDto.getLastName());

    }

    @Test
    @DisplayName(value = "Test API testClassValidation with Ibs Null Test")
    void testClassValidationWithIbsNull() {

        ConsultaIbsResponse responseIbs = new ConsultaIbsResponse();
        ConsultaIbsResponseData data = new ConsultaIbsResponseData();
        data.setBody(null);
        responseIbs.setData(data);

        ValidacionTarjetaResponse cmc = new ValidacionTarjetaResponse();
        ValidacionTarjetaDataResponse cmcData = new ValidacionTarjetaDataResponse();
        ValidacionTarjetaBodyResponse validacionTarjetaBodyResponse = new ValidacionTarjetaBodyResponse();
        validacionTarjetaBodyResponse.setNameMagneticStripe("CMC name Magnetic");
        validacionTarjetaBodyResponse.setLastNameMagneticStripe("CMC last name Magnetic");
        validacionTarjetaBodyResponse.setShortName("CMC short Name");
        cmcData.setBody(validacionTarjetaBodyResponse);
        cmc.setData(cmcData);

        AccountInformationDto accountInformationDto = components.checksFullNameClient(cmc, responseIbs);

        Assertions.assertEquals(validacionTarjetaBodyResponse.getShortName(), accountInformationDto.getShortName());
        Assertions.assertEquals(validacionTarjetaBodyResponse.getNameMagneticStripe(), accountInformationDto.getName());
        Assertions.assertEquals(validacionTarjetaBodyResponse.getLastNameMagneticStripe(), accountInformationDto.getLastName());

    }

    @Test
    @DisplayName(value = "Test API testClassValidation with CMC AND Ibs Null Test")
    void testClassValidationWithIbsAndCMCNull() {

        ConsultaIbsResponse responseIbs = new ConsultaIbsResponse();
        ConsultaIbsResponseData data = new ConsultaIbsResponseData();
        data.setBody(null);
        responseIbs.setData(data);

        ValidacionTarjetaResponse cmc = new ValidacionTarjetaResponse();
        ValidacionTarjetaDataResponse cmcData = new ValidacionTarjetaDataResponse();
        cmcData.setBody(null);
        cmc.setData(cmcData);

        Assertions.assertThrows(BusinessException.class, () -> components.checksFullNameClient(cmc, responseIbs));
    }
}
