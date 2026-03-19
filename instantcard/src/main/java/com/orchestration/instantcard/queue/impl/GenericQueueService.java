package com.orchestration.instantcard.queue.impl;

import com.google.gson.Gson;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.Metadata;
import com.orchestration.instantcard.models.queues.QueueParameters;

import com.orchestration.instantcard.queue.ColasServicesConsumo;
import com.orchestration.instantcard.queue.QueueService;
import com.orchestration.instantcard.utils.DurationUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Objects;

@Component
public class GenericQueueService<T, U> implements QueueService<T, U> {

    private ColasServicesConsumo queueService;
    private DurationUtils durationUtils;
    private ApiContext apiContext;

    private static final Logger logger = LoggerFactory.getLogger(GenericQueueService.class);

    public GenericQueueService(ColasServicesConsumo queueService, DurationUtils durationUtils, ApiContext apiContext) {
        this.queueService = queueService;
        this.durationUtils = durationUtils;
        this.apiContext = apiContext;
    }

    @Override
    public T consumeQueueAzure(U args, QueueParameters queueParameters, Class<T> typeRes)
            throws BusinessException {

        logger.info("Inicio Proceso Cola -> Enviando mensaje tipo request: {}, serviceid: {}", args.getClass().getSimpleName(),queueParameters.getServiceId());
        long startTime = System.currentTimeMillis();
        T queueResponse = null;
        Gson g = new Gson();

        String randomSessionId = RandomStringUtils.random(24, 0,0, true,true,null, new SecureRandom()).toUpperCase();
        String finalMessage = "";
        Metadata metadata= apiContext.getMetadata();
        logger.info("Session id: {}, messageId: {}, messageIdOrg: {}, shortMessageId: {} ," +
                        "applicationId: {}", randomSessionId
                ,metadata.getMessageId(),
                metadata.getMessageIdOrg(),metadata.getShortMessageId(),
                metadata.getApplicationId());
        logger.info("================Inicio metodo senderToQueue===================");
        long startTimeSender = System.currentTimeMillis();
        boolean repuesta = queueService.senderToQueue(queueParameters.getSenderQueueName(), randomSessionId, args, queueParameters.getServiceId());
        durationUtils.durationExecutions(startTimeSender ,randomSessionId
                ,"================Fin metodo senderToQueue===================");
        String simpleNameResponse="";
        if (repuesta) {
            logger.info("================Inicio metodo receiverFormQueue===================");
            long startTimeReceiver = System.currentTimeMillis();
            finalMessage = queueService.receiverFormQueue(randomSessionId, queueParameters.getReceiverQueueName());
            durationUtils.durationExecutions(startTimeReceiver ,randomSessionId
                    ,"================Fin metodo receiverFormQueue===================");
            queueResponse = g.fromJson(finalMessage, typeRes);
            if(Objects.nonNull(queueResponse)){
                simpleNameResponse=queueResponse.getClass().getSimpleName();
            }
        }
        String message= String.format("Fin Proceso cola - Duracion Total -> Respuesta mensaje: %s serviceid: %s",
                simpleNameResponse,queueParameters.getServiceId());
        durationUtils.durationExecutions(startTime ,randomSessionId,message);
        return queueResponse;
    }

}
