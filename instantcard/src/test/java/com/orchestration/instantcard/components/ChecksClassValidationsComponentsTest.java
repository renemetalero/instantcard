package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.vision.CreateClientComponent;
import com.orchestration.instantcard.components.vision.CreateInstantCardComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.FieldsCardDto;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.utils.Authorization;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChecksClassValidationsComponentsTest {

    private ChecksClassValidationsComponents checksClassValidationsComponents;

    @MockBean
    private Authorization authorizations;
    @Mock
    private CreateClientComponent createClientComponent;
    @Mock
    private CreateInstantCardComponent createInstantCardComponent;
    @Mock
    private ApiContext apiContext;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.authorizations = new Authorization(apiContext);
        this.checksClassValidationsComponents = new ChecksClassValidationsComponents(authorizations, createClientComponent, createInstantCardComponent);
    }

    @Test
    @DisplayName(value = "Test Process Type Card")
    void testProcessTypeCard() {
        InstantCardRequest requestData = create.createInstantCard();
        AccountInformationDto accountInformationDto = create.createAccountInformationDto();
        ConsultaIbsResponse responseIbs = create.createValidateDataIbsResponse();

        InstantCardResponseBody instantCardResponseBody = create.createInstantCardResponseBody();
        when(this.createInstantCardComponent.createInstantCard(any(InstantCardRequest.class),any(FieldsCardDto.class)))
                .thenReturn(instantCardResponseBody);

        when(this.apiContext.getMetadata()).thenReturn(create.createInstantCard().getMetadata());

        ResponseEntity<InstantCardResponse> processTypeCard = this.checksClassValidationsComponents.processTypeCard(requestData, accountInformationDto, responseIbs);

        Assertions.assertEquals(200, processTypeCard.getStatusCode().value());

    }

    @Test
    @DisplayName(value = "Test Process Type Card - testProcessTypeCardWithError")
    void testProcessTypeCardWithError() {
        InstantCardRequest requestData = new InstantCardRequest();
        InstantCardRequestData instantCardRequestData = new InstantCardRequestData();
        instantCardRequestData.setBody(null);
        requestData.setData(instantCardRequestData);
        AccountInformationDto accountInformationDto = create.createAccountInformationDto();
        ConsultaIbsResponse responseIbs = create.createValidateDataIbsResponse();
        Assertions.assertThrows(BusinessException.class,
                () -> this.checksClassValidationsComponents.processTypeCard(requestData, accountInformationDto, responseIbs));
    }
}
