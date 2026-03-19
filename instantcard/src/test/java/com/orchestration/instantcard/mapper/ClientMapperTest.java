package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteBody;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;

import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    @InjectMocks
    private CreatePojos create;

    @Spy
    private ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Mock
    private ApiContext apiContext;

    @BeforeEach
    void setUp() {
        apiContext.setContextMetadata(create.createInstantCard().getMetadata());
    }

    @Test
    @DisplayName(value = "Validar proceso de ConvertClientBody")
    void ScenarioConvertClientBody() {
        CreacionClienteBody clientObj = mapper.modelToClienteBodyDto(new ConsultaIbsResponseBody());
        Assertions.assertNotNull(clientObj);
    }

    @Test
    @DisplayName(value = "Validar proceso de ConvertVisionRequest")
    void consultaDisp200OK() throws Exception {
        ConsultaIbsResponseBody body=new ConsultaIbsResponseBody();
        apiContext.setContextMetadata(null);
        body.setCustomerType("0");
        body.setFlagIdentificationNumberResponseIbs("S");
        AccountInformationDto accountInformationDto = create.createAccountInformationDto();
        CreacionClienteVisionRequestBody clientObj = mapper.modelToVisionRequest(body, accountInformationDto);

        mapper.afterMappingClient(new CreacionClienteVisionRequestBody());
        mapper.customerType("0");
        mapper.flagVision("S");
        mapper.validateNameLine("",0);
        mapper.formatInteger("");
        mapper.modelToClienteBodyDto(new ConsultaIbsResponseBody());

        Assertions.assertNotNull(clientObj);
    }

}
