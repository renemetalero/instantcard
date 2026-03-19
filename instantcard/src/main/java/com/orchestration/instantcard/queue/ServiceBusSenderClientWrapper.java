package com.orchestration.instantcard.queue;

import com.azure.messaging.servicebus.ServiceBusMessage;

public interface ServiceBusSenderClientWrapper {
    void sendMessage(ServiceBusMessage message);
    String getIdentifier();
}
