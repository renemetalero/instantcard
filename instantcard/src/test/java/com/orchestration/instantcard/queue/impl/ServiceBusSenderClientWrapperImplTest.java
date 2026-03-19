package com.orchestration.instantcard.queue.impl;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceBusSenderClientWrapperImplTest {

    @Mock
    private ServiceBusSenderClient senderClient;

    @Test
    @DisplayName(value = "Test API sendMessage")
    void testSendMessage() {
        Mockito.doNothing().when(senderClient).sendMessage(Mockito.any(ServiceBusMessage.class));
        ServiceBusSenderClientWrapperImpl serviceBusSenderClientWrapperImpl = new ServiceBusSenderClientWrapperImpl(senderClient);
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("test message");
        serviceBusSenderClientWrapperImpl.sendMessage(serviceBusMessage);
        Mockito.verify(senderClient, Mockito.times(1)).sendMessage(Mockito.any(ServiceBusMessage.class));
    }

    @Test
    @DisplayName(value = "Test API getIdentifier")
    void testGetIdentifier() {
        Mockito.when(senderClient.getIdentifier()).thenReturn("test identifier");
        ServiceBusSenderClientWrapperImpl serviceBusSenderClientWrapperImpl = new ServiceBusSenderClientWrapperImpl(senderClient);
        serviceBusSenderClientWrapperImpl.getIdentifier();
        Assertions.assertEquals("test identifier", serviceBusSenderClientWrapperImpl.getIdentifier());
    }
}
