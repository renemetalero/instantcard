package com.orchestration.instantcard.service.vision;


import com.orchestration.instantcard.exception.ValidIbsResponse;
import com.orchestration.instantcard.models.queues.QueueParameters;
import com.orchestration.instantcard.queue.impl.GenericQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VisionServiceImpl<T, R>  implements ValidIbsResponse, VisionService<T, R>  {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${service-id.vision-customer-add}")
    private String serviceCreateCustomerId;

    @Value("${service-id.vision-sendername}")
    private String visionSenderName;

    @Value("${service-id.vision-receivername}")
    private String visionReceiverName;

    private GenericQueueService<T, R> visionServiceCustomer;

    public VisionServiceImpl(GenericQueueService<T, R> visionServiceCustomer) {
        this.visionServiceCustomer = visionServiceCustomer;
    }

    public T consumeVisionCustomerAdd(R data, Class<T> responseClass) {
        logger.info("Enviando mensaje a vision+ crear cliente");
        QueueParameters queueVisionParameters = new QueueParameters(serviceCreateCustomerId, visionSenderName, visionReceiverName);
        return visionServiceCustomer
                .consumeQueueAzure(data, queueVisionParameters, responseClass);
    }

}
