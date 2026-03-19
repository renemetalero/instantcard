package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreacionClienteRequestTest {

    @InjectMocks
    private CreatePojos create;

    @Test
    @DisplayName(value = "CreacionClienteRequestTest ==> testCreacionClienteRequest")
    void testCreacionClienteRequest() {
        CreacionClienteRequest creacionClienteRequest = create.creacionClienteRequest();
        Assertions.assertNotNull(creacionClienteRequest.getData().getBody().getCustomerNameLine1());
        Assertions.assertNotNull(creacionClienteRequest.getData().getBody().getShortName());
        Assertions.assertNotNull(creacionClienteRequest.getMetadata());
    }

}
