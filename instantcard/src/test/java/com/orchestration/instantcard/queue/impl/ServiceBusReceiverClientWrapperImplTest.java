package com.orchestration.instantcard.queue.impl;

import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServiceBusReceiverClientWrapperImplTest {

    @Mock
    private ServiceBusSessionReceiverClient sessionReceiverClient;

    private ServiceBusReceiverClient receiverClient;

    @BeforeEach
    void setUp() {
        receiverClient = Mockito.mock(ServiceBusReceiverClient.class);
        Mockito.when(sessionReceiverClient.acceptSession(Mockito.anyString())).thenReturn(receiverClient);
    }


    @Test
    @DisplayName(value = "Test API receiveMessage")
    void testReceiveMessage() {
        List<ServiceBusReceivedMessage> messageList = new ArrayList<>();
        ServiceBusReceivedMessage message = Mockito.mock(ServiceBusReceivedMessage.class);
        messageList.add(message);

        IterableStream<ServiceBusReceivedMessage> messages = new IterableStream<>(messageList);
        Mockito.when(receiverClient.receiveMessages(Mockito.anyInt(), Mockito.any(Duration.class))).thenReturn(messages);

        ServiceBusReceiverClientWrapperImpl serviceBusReceiverClientWrapperImpl = new ServiceBusReceiverClientWrapperImpl(sessionReceiverClient);
        serviceBusReceiverClientWrapperImpl.acceptSession("sessionIdTest");
        serviceBusReceiverClientWrapperImpl.receiveMessages(1, Duration.ofSeconds(10));

        Mockito.verify(sessionReceiverClient, Mockito.times(2)).acceptSession(Mockito.anyString());
        Mockito.verify(receiverClient, Mockito.times(1)).receiveMessages(Mockito.anyInt(), Mockito.any(Duration.class));

    }
}
