package com.orchestration.instantcard.queue;

import com.orchestration.instantcard.exception.models.BusinessException;

public interface ColasServicesConsumo {
    boolean senderToQueue(String senderQueue, String randomSessionId, Object request, String serviceId) throws BusinessException;
    String receiverFormQueue(String randomSessionId, String receiverQueue) throws BusinessException;
}
