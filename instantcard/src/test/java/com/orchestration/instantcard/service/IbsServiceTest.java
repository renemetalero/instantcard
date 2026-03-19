package com.orchestration.instantcard.service;

import com.azure.core.util.BinaryData;
import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.*;
import com.google.gson.Gson;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequest;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseData;
import com.orchestration.instantcard.queue.ColasServicesConsumo;
import com.orchestration.instantcard.queue.impl.GenericQueueService;
import com.orchestration.instantcard.queue.ServiceBusReceiverClientWrapper;
import com.orchestration.instantcard.queue.ServiceBusSenderClientWrapper;
import com.orchestration.instantcard.queue.impl.ColasServicesConsumoImpl;
import com.orchestration.instantcard.service.ibs.IbsServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.DurationUtils;
import com.orchestration.instantcard.utils.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class IbsServiceTest {

    private IbsServiceImpl ibsServiceImpl;

    @InjectMocks
    private CreatePojos create;

    @Mock(name = "senderIbs")
    private ServiceBusSenderClientWrapper senderIbs;

    @Mock(name = "receiverIbs")
    private ServiceBusReceiverClientWrapper receiverIbs;

    @MockBean
    private GenericQueueService<ConsultaIbsResponse, ConsultaIbsRequest> genericQueueService;

    @MockBean
    private GenericQueueService<ConsultaIbsResponse, ConsultaIbsRequest> ibsService;

    @MockBean
    private ColasServicesConsumo colasServicesConsumo;

    @Mock
    private ApiContext apiContext;

    @BeforeEach
    void setUp() {
        this.apiContext = new ApiContext();
        this.apiContext.setContextMetadata(create.createInstantCard().getMetadata());

        this.colasServicesConsumo = new ColasServicesConsumoImpl(senderIbs, receiverIbs, senderIbs, receiverIbs);
        ReflectionTestUtils.setField(this.colasServicesConsumo, "SERVICE_IDIBS", TestsConstants.SERVICE_ID_IBS);

        this.ibsService = new GenericQueueService<>(colasServicesConsumo, new DurationUtils(apiContext), apiContext);

        this.ibsServiceImpl = new IbsServiceImpl(ibsService);
        ReflectionTestUtils.setField(this.ibsServiceImpl, "serviceIdIBS", TestsConstants.SERVICE_ID_IBS);
        ReflectionTestUtils.setField(this.ibsServiceImpl, "ibsSenderName", "test.to.test.inte.r.lq");
        ReflectionTestUtils.setField(this.ibsServiceImpl, "ibsReceiverName", "test.to.test.inte.r.lq");
    }

    @ParameterizedTest
    @MethodSource("provideMessageResponse")
    @DisplayName(value = "Test send message to ibs azure bus service Mock")
    void sendMessageIbsAzureBusService(BinaryData bodyReturn) {
        Mockito.doNothing().when(senderIbs).sendMessage(Mockito.any(ServiceBusMessage.class));

        ServiceBusReceiverClient mockReceiverClient = Mockito.mock(ServiceBusReceiverClient.class);
        ServiceBusReceivedMessage mockMessage = Mockito.mock(ServiceBusReceivedMessage.class);
        Mockito.when(mockMessage.getBody()).thenReturn(bodyReturn);
        IterableStream<ServiceBusReceivedMessage> messages = new IterableStream<>(Collections.singletonList(mockMessage));
        Mockito.when(receiverIbs.acceptSession(Mockito.anyString())).thenReturn(mockReceiverClient);
        Mockito.when(mockReceiverClient.receiveMessages(Mockito.anyInt(), Mockito.any(Duration.class))).thenReturn(messages);

        this.ibsServiceImpl.validateDataIbs(create.createFindIbs(), ConsultaIbsResponse.class);
        Mockito.verify(senderIbs, Mockito.times(1)).sendMessage(Mockito.any(ServiceBusMessage.class));
    }

    @Test
    @DisplayName(value = "Test sender exception message from ibs azure bus service Mock")
    void senderExceptionIbsAzureBusService() {
        Mockito.doNothing().when(senderIbs).sendMessage(Mockito.any(ServiceBusMessage.class));
        Mockito.when(receiverIbs.acceptSession(Mockito.anyString())).thenThrow(new NullPointerException("Simulando NullPointerException en Vision+"));

        try {
            this.ibsServiceImpl.validateDataIbs(create.createFindIbs(), ConsultaIbsResponse.class);
        } catch (BusinessException be) {
            assertNotNull(be);
            assertEquals(TestsConstants.BUSSINESS_ERROR_CODE_ZERO, be.getCode());
            assertEquals(TestsConstants.QUEUE_RECEIVER_ERROR_MESSAGE, be.getMessage());
        }
        Mockito.verify(senderIbs, Mockito.times(1)).sendMessage(Mockito.any(ServiceBusMessage.class));
    }

    @Test
    @DisplayName(value = "Test receive exception message from ibs azure bus service Mock")
    void receiveExceptionIbsAzureBusService() {

        List<ErrorModel> errors = List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_IBS.getCode(),
                InstantCardEnumError.BUSINESS_IBS.getTitle(),
                InstantCardEnumError.BUSINESS_IBS.getMessage()));

        Mockito.doThrow(new BusinessException(TestsConstants.INTERNAL_SERVER_ERROR,errors))
                .when(senderIbs).sendMessage(Mockito.any(ServiceBusMessage.class));

        ConsultaIbsRequest createFinds = create.createFindIbs();
        BusinessException be = Assertions.assertThrows(BusinessException.class, () -> {
            this.ibsServiceImpl.validateDataIbs(createFinds, ConsultaIbsResponse.class);
        });
        Assertions.assertNotNull(createFinds.getMetadata());
        Assertions.assertEquals(TestsConstants.BUSSINESS_ERROR_CODE_ZERO, be.getCode());
        Assertions.assertEquals(TestsConstants.QUEUE_SENDER_ERROR_MESSAGE, be.getMessage());

    }

    static Stream<Arguments> provideMessageResponse() {
        Gson g = new Gson();
        ConsultaIbsResponse consultaIbsResponse = new ConsultaIbsResponse();
        ConsultaIbsResponseData consultaIbsResponseData = new ConsultaIbsResponseData();
        ConsultaIbsResponseBody consultaIbsResponseBody = new ConsultaIbsResponseBody();
        consultaIbsResponseBody.setCustomerNumber(TestsConstants.CUSTOMER_NUMBER_TEST);
        consultaIbsResponseData.setBody(consultaIbsResponseBody);
        consultaIbsResponse.setData(consultaIbsResponseData);

        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(BinaryData.fromString(g.toJson(consultaIbsResponse)))
        );
    }
}
