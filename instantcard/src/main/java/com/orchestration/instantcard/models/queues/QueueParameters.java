package com.orchestration.instantcard.models.queues;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class QueueParameters {
    private String serviceId;
    private String senderQueueName;
    private String receiverQueueName;
}
