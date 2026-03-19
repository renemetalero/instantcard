package com.orchestration.instantcard.queue;

import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.queues.QueueParameters;

public interface QueueService<T, U> {
    T consumeQueueAzure(U args, QueueParameters queueParameters, Class<T> typeRes) throws BusinessException, InterruptedException;
}
