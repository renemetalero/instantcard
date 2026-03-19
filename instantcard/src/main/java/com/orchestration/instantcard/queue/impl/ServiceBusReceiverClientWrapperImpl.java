package com.orchestration.instantcard.queue.impl;

import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.orchestration.instantcard.queue.ServiceBusReceiverClientWrapper;

import java.time.Duration;

public class ServiceBusReceiverClientWrapperImpl implements ServiceBusReceiverClientWrapper {

    private ServiceBusSessionReceiverClient receiverClient;

    private String sessionId;

    public ServiceBusReceiverClientWrapperImpl(ServiceBusSessionReceiverClient receiverClient) {
        this.receiverClient = receiverClient;
    }

    @Override
    public ServiceBusReceiverClient acceptSession(String sessionId) {
        this.sessionId = sessionId;
        return receiverClient.acceptSession(sessionId);
    }

    @Override
    public IterableStream<ServiceBusReceivedMessage> receiveMessages(int maxMessages, Duration maxWaitTime) {
        return this.receiverClient.acceptSession(this.sessionId).receiveMessages(maxMessages, maxWaitTime);
    }


}
