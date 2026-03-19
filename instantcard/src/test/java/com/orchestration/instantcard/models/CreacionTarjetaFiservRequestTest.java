package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreacionTarjetaFiservRequestTest {
    private CreacionTarjetaFiservRequest creacionTarjetaFiservRequest;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        creacionTarjetaFiservRequest = create.createCreacionTarjetaFiservRequest();
    }

    @Test
    @DisplayName(value = "Test CreacionTarjetaFiservRequest")
    void testModelCreacionTarjetaFiservRequestTest() {
        creacionTarjetaFiservRequest.getAccountCreateFlag();
        creacionTarjetaFiservRequest.getCardActionFlag();
        creacionTarjetaFiservRequest.getCustomerNumber();
        creacionTarjetaFiservRequest.getFirstPurchaseFlag();
        creacionTarjetaFiservRequest.getLogo();
        creacionTarjetaFiservRequest.getOrganizationNumber();
        creacionTarjetaFiservRequest.getSameDayProcessingType();
        creacionTarjetaFiservRequest.getAccountData();
        creacionTarjetaFiservRequest.getEmbosserData();
        Assertions.assertNotNull(creacionTarjetaFiservRequest);
    }

}
