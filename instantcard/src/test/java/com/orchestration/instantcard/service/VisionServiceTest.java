package com.orchestration.instantcard.service;

import com.azure.core.util.BinaryData;
import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.google.gson.Gson;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequest;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseBody;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseData;
import com.orchestration.instantcard.queue.ColasServicesConsumo;
import com.orchestration.instantcard.queue.impl.GenericQueueService;
import com.orchestration.instantcard.queue.ServiceBusReceiverClientWrapper;
import com.orchestration.instantcard.queue.ServiceBusSenderClientWrapper;
import com.orchestration.instantcard.queue.impl.ColasServicesConsumoImpl;
import com.orchestration.instantcard.service.vision.VisionServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisionServiceTest {

    private VisionServiceImpl visionServiceImpl;

    @MockBean
    GenericQueueService<CreacionClienteVisionResponse, CreacionClienteVisionRequest> visionServiceCustome;
    @InjectMocks
    private CreatePojos create;

    @Mock(name = "senderVision")
    private ServiceBusSenderClientWrapper senderVision;

    @Mock(name = "receiverVision")
    private ServiceBusReceiverClientWrapper receiverVision;
    @Mock
    private ApiContext apiContext;
    @MockBean
    private ColasServicesConsumo colasServicesConsumo;

    @BeforeEach
    void setUp() {
        this.apiContext = new ApiContext();
        this.apiContext.setContextMetadata(create.createInstantCard().getMetadata());
        this.colasServicesConsumo = new ColasServicesConsumoImpl(senderVision, receiverVision, senderVision, receiverVision);
        ReflectionTestUtils.setField(this.colasServicesConsumo, "SERVICE_IDIBS", TestsConstants.SERVICE_ID_IBS);
        this.visionServiceCustome = new GenericQueueService<>(this.colasServicesConsumo, new DurationUtils(apiContext), apiContext);
        this.visionServiceImpl = new VisionServiceImpl(visionServiceCustome);
        ReflectionTestUtils.setField(this.visionServiceImpl, "serviceCreateCustomerId", "another");
        ReflectionTestUtils.setField(this.visionServiceImpl, "visionSenderName", "test.to.test.inte.r.lq");
        ReflectionTestUtils.setField(this.visionServiceImpl, "visionReceiverName", "test.to.test.inte.r.lq");
    }

    @ParameterizedTest
    @MethodSource("provideMessageResponse")
    @DisplayName(value = "Test receive message from vision azure bus service Mock")
    void receiveMessageIbsAzureBusService(BinaryData bodyReturn) {

        Mockito.doNothing().when(senderVision).sendMessage(Mockito.any(ServiceBusMessage.class));

        ServiceBusReceiverClient mockReceiverClient = Mockito.mock(ServiceBusReceiverClient.class);
        ServiceBusReceivedMessage mockMessage = Mockito.mock(ServiceBusReceivedMessage.class);
        Mockito.when(mockMessage.getBody()).thenReturn(bodyReturn);
        IterableStream<ServiceBusReceivedMessage> messages = new IterableStream<>(Collections.singletonList(mockMessage));

        Mockito.when(receiverVision.acceptSession(Mockito.anyString())).thenReturn(mockReceiverClient);
        Mockito.when(mockReceiverClient.receiveMessages(Mockito.anyInt(), Mockito.any(Duration.class))).thenReturn(messages);

        this.visionServiceImpl.consumeVisionCustomerAdd(create.createFindVision(), CreacionClienteVisionResponse.class);

        Mockito.verify(senderVision, Mockito.times(1)).sendMessage(Mockito.any(ServiceBusMessage.class));
    }

    @Test
    @DisplayName(value = "Test sender exception message from vision azure bus service Mock")
    void senderExceptionVisionAzureBusService() {
        Mockito.doNothing().when(senderVision).sendMessage(Mockito.any(ServiceBusMessage.class));
        Mockito.when(receiverVision.acceptSession(Mockito.anyString())).thenThrow(new NullPointerException("Simulando NullPointerException en Vision+"));

        try {
            this.visionServiceImpl.consumeVisionCustomerAdd(create.createFindVision(), CreacionClienteVisionResponse.class);
        } catch (BusinessException be) {
            assertNotNull(be);
            assertEquals(TestsConstants.BUSSINESS_ERROR_CODE_ZERO, be.getCode());
            assertEquals(TestsConstants.QUEUE_RECEIVER_ERROR_MESSAGE, be.getMessage());
        }
        Mockito.verify(senderVision, Mockito.times(1)).sendMessage(Mockito.any(ServiceBusMessage.class));
    }

    @Test
    @DisplayName(value = "Test receive exception message from vision azure bus service Mock")
    void receiveExceptionIbsAzureBusService() {

        List<ErrorModel> errors = List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_VISION.getCode(),
                InstantCardEnumError.BUSINESS_VISION.getTitle(),
                InstantCardEnumError.BUSINESS_VISION.getMessage()));

        Mockito.doThrow(new BusinessException(TestsConstants.INTERNAL_SERVER_ERROR,errors))
                .when(senderVision).sendMessage(Mockito.any(ServiceBusMessage.class));

        CreacionClienteVisionRequest createFindVision = create.createFindVision();
        BusinessException be = Assertions.assertThrows(BusinessException.class, () -> {
            this.visionServiceImpl.consumeVisionCustomerAdd(createFindVision, CreacionClienteVisionResponse.class);
        });
        assertNotNull(be);
        assertEquals(TestsConstants.BUSSINESS_ERROR_CODE_ZERO, be.getCode());
        assertEquals(TestsConstants.QUEUE_SENDER_ERROR_MESSAGE, be.getMessage());
    }


    static Stream<Arguments> provideMessageResponse() {
        Gson g = new Gson();
        CreacionClienteVisionResponse creacionClienteVisionResponse = new CreacionClienteVisionResponse();
        CreacionClienteVisionResponseData creacionClienteVisionResponseData = new CreacionClienteVisionResponseData();
        CreacionClienteVisionResponseBody creacionClienteVisionResponseBody = new CreacionClienteVisionResponseBody();
        creacionClienteVisionResponseBody.setCustomerNumber(TestsConstants.CUSTOMER_NUMBER_TEST);
        creacionClienteVisionResponseData.setBody(creacionClienteVisionResponseBody);
        creacionClienteVisionResponse.setData(creacionClienteVisionResponseData);
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(BinaryData.fromString(g.toJson(creacionClienteVisionResponse)))
        );
    }


}
