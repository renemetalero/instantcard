package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultaIbsRequestTest {
    @InjectMocks
    private CreatePojos create;

    @Test
    void testConsultaIbsRequest() {
        ConsultaIbsRequest consultaIbsRequest = create.createConsultaIbsRequest();
        Assertions.assertNotNull(consultaIbsRequest.getMetadata());
        Assertions.assertNotNull(consultaIbsRequest.getData());
        Assertions.assertNotNull(consultaIbsRequest.getData().getHeader());
        Assertions.assertNotNull(consultaIbsRequest.getData().getBody());
        Assertions.assertNotNull(consultaIbsRequest.getData().getBody().getCustomerNumber());
    }
}
