package com.orchestration.instantcard.service.ibs;


import com.orchestration.instantcard.models.queues.QueueParameters;
import com.orchestration.instantcard.queue.impl.GenericQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IbsServiceImpl<T,R> implements IbsService<T,R> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${service-id.ibs}")
    private String serviceIdIBS;
    @Value("${service-id.ibs-sendername}")
    private String ibsSenderName;
    @Value("${service-id.ibs-receivername}")
    private String ibsReceiverName;

    private GenericQueueService<T, R> ibsService;

    public IbsServiceImpl(GenericQueueService<T,R> ibsService) {
        this.ibsService = ibsService;
    }

    public T validateDataIbs(R data, Class<T> responseClass) {
        logger.info("Enviando mensaje a ibs");
        QueueParameters queueIbsParameters = new QueueParameters(serviceIdIBS, ibsSenderName, ibsReceiverName);
        return ibsService.consumeQueueAzure(data, queueIbsParameters, responseClass);
    }
}
