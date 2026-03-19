package com.orchestration.instantcard.components;


import com.orchestration.instantcard.components.ibs.DataValidationsIbsComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequest;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.service.ibs.IbsServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataValidationsIbsComponentTest {

    private DataValidationsIbsComponent dataValidationsIbsComponent;

    @Mock
    IbsServiceImpl serviceIbs;
    @Mock
    ApiContext apiContext;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        dataValidationsIbsComponent = new DataValidationsIbsComponent(this.serviceIbs, this.apiContext);
    }

    @Test
    void testValidateIbs() {
        String customerNumber = "094738372494";

        when(serviceIbs.validateDataIbs(any(ConsultaIbsRequest.class), Mockito.any(Class.class)))
                .thenReturn(create.createValidateDataIbsResponse());

        ConsultaIbsResponse consultaIbsResponse = this.dataValidationsIbsComponent.dataValidation(create.createValidacionTarjetaDataResponse(), create.createHeader(), customerNumber);
        assertNotNull(consultaIbsResponse);

    }
}
