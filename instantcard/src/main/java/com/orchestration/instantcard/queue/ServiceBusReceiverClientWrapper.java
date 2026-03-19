package com.orchestration.instantcard.queue;

import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;

import java.time.Duration;

public interface ServiceBusReceiverClientWrapper {
    ServiceBusReceiverClient acceptSession(String sessionId);
    IterableStream<ServiceBusReceivedMessage> receiveMessages(int maxMessages, Duration maxWaitTime);
}
