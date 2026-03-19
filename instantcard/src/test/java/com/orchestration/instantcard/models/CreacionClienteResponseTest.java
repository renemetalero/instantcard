package com.orchestration.instantcard.models;

import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponseBody;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponseData;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreacionClienteResponseTest {
    private CreacionClienteResponse creacionClienteResponse;

    @InjectMocks
    private CreatePojos create;

    @Test
    void testCreacionClienteResponse() {
        creacionClienteResponse = new CreacionClienteResponse();
        CreacionClienteResponseData creacionClienteResponseData = new CreacionClienteResponseData();
        CreacionClienteResponseBody creacionClienteResponseBody = new CreacionClienteResponseBody();
        creacionClienteResponseBody.setMsj("Msj");
        creacionClienteResponseBody.setCustomerWasCreated("CustomerWasCreated");
        creacionClienteResponseData.setBody(creacionClienteResponseBody);
        creacionClienteResponse.setData(creacionClienteResponseData);

        List<ErrorModel> errorModelList = List.of(new ErrorModel());
        creacionClienteResponse.setErrors(errorModelList);
        creacionClienteResponse.setMetadata(creacionClienteResponse.getMetadata());
        creacionClienteResponse.setMetadata(create.createMeta());
        creacionClienteResponse.getData().getBody().getMsj();
        creacionClienteResponse.getData().getBody().getCustomerWasCreated();

        Assertions.assertNotNull(creacionClienteResponse.getData().getBody());

    }
}
