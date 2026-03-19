package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestData;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CmcInstantCardRequestTest {
    CmcInstantCardRequest cmcInstantCardRequest;

    @InjectMocks
    private CreatePojos create;

    @Test
    void testCmcInstantCardRequest() {
        cmcInstantCardRequest = new CmcInstantCardRequest();

        CmcInstantCardRequestData cmcInstantCardRequestData = create.createCmcInstantCardRequestData();
        cmcInstantCardRequest.setData(cmcInstantCardRequestData);
        cmcInstantCardRequest.setMetadata(create.createMeta());

        cmcInstantCardRequest.getData().getBody().getCustomerNumber();
        cmcInstantCardRequest.getData().getBody().getLogo();
        cmcInstantCardRequest.getData().getBody().getCardNumber();
        cmcInstantCardRequest.getData().getBody().getAccountNumber();
        cmcInstantCardRequest.getData().getBody().getProductType();
        cmcInstantCardRequest.getData().getBody().getCardCycle();
        cmcInstantCardRequest.getData().getBody().getPct();
        cmcInstantCardRequest.getData().getBody().getFecha();
        cmcInstantCardRequest.getData().getBody().getUser();

        Assertions.assertNotNull(cmcInstantCardRequest.getData().getBody());
        Assertions.assertNotNull(cmcInstantCardRequest.getMetadata());
    }

}
