package com.orchestration.instantcard.queue.impl;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.orchestration.instantcard.queue.ServiceBusSenderClientWrapper;


public class ServiceBusSenderClientWrapperImpl implements ServiceBusSenderClientWrapper {
    private ServiceBusSenderClient senderClient;
    public ServiceBusSenderClientWrapperImpl(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }
    @Override
    public void sendMessage(ServiceBusMessage message) {
        senderClient.sendMessage(message);
    }

    @Override
    public String getIdentifier(){
        return senderClient.getIdentifier();
    }
}
