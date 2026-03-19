package com.orchestration.instantcard.queue.impl;

import com.azure.core.util.BinaryData;
import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.*;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.queue.ServiceBusReceiverClientWrapper;
import com.orchestration.instantcard.queue.ServiceBusSenderClientWrapper;
import com.orchestration.instantcard.queue.ColasServicesConsumo;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class ColasServicesConsumoImpl implements ColasServicesConsumo {

    public static final String BABROKER = "babroker";  

    @Value("${service-id.ibs}")
    private String SERVICE_IDIBS;

    @Value("${timeOutReceiverClient}")
    private int queueTimeOut;

    private ServiceBusSenderClientWrapper senderIbs;
    private ServiceBusReceiverClientWrapper receiverIbs;
    private ServiceBusSenderClientWrapper senderVision;
    private ServiceBusReceiverClientWrapper receiverVision;

    private static final Logger logger = LoggerFactory.getLogger(ColasServicesConsumoImpl.class);

    public ColasServicesConsumoImpl(
    	ServiceBusSenderClientWrapper senderIbs, 
    	ServiceBusReceiverClientWrapper receiverIbs, 
    	ServiceBusSenderClientWrapper senderVision, 
    	ServiceBusReceiverClientWrapper receiverVision) {
        this.senderIbs = senderIbs;
        this.receiverIbs = receiverIbs;
        this.senderVision = senderVision;
        this.receiverVision = receiverVision;
    }

    @Override
    public boolean senderToQueue(String senderQueue, String randomSessionId, Object request, String serviceId)
            throws BusinessException {
        logger.info("===============Inicio de senderToQueue [{}] sessionId {}", senderQueue, randomSessionId);
        boolean res = false;

        try {
            logger.info("===============Creando Session de senderToQueue [{}] sessionId {}", senderQueue, randomSessionId);
            ServiceBusMessage message = new ServiceBusMessage(BinaryData.fromObject(request));
            message.setContentType("application/json");
            message.setSessionId(randomSessionId);
            message.getApplicationProperties().put("serviceId", serviceId);
            logger.info("===============Enviando mensaje a la cola [{}] with sessionId [{}] and serviceId [{}]", senderQueue, randomSessionId, serviceId);
            if (SERVICE_IDIBS.equals(serviceId)) {
                logger.info("senderIbs {}",senderIbs.getIdentifier());
                logger.info("senderIbs.hashCode {}",senderIbs.hashCode());
                senderIbs.sendMessage(message);
            } else {
                logger.info("senderVision {}",senderVision.getIdentifier());
                logger.info("senderVision.hashCode {}",senderVision.hashCode());
                senderVision.sendMessage(message);
            }
            res = true;
            logger.info("===============Enviado correctamente a la cola [{}] sessionId {}", senderQueue, randomSessionId);
        } catch (Exception ex) {
            logger.error("===============Error al enviar mensaje a la cola [{}]: {} ", senderQueue, ex);
            logger.info("================== sessionId {}", randomSessionId);
            throw new BusinessException("0", InstantCardConstants.BUSINESS_EXC_SEND_MESSAGE, null);
        } finally {
            logger.info("===============Se cerro la conexion de sessionId {}", randomSessionId);
        }
        logger.info("===============fin de  senderToQueue [{}] sessionId {}", senderQueue, randomSessionId);
        return res;
    }

    @Override
    public String receiverFormQueue(String randomSessionId, String receiverQueue)
            throws BusinessException {
        logger.info("===============Inicio de receiverFormQueue [{}] sessionId {}", receiverQueue, randomSessionId);
        String finalResponse = null;
        ServiceBusReceiverClient receiver = null;

        try {
            logger.info("===============Creando Session de receiverFormQueue [{}] sessionId {}", receiverQueue, randomSessionId);
            if (receiverQueue.startsWith(BABROKER)) {
                logger.info("receiverIbs {}",receiverIbs.hashCode());

                receiver = receiverIbs.acceptSession(randomSessionId);
            } else {
                logger.info("receiverVision {}",receiverVision.hashCode());
                receiver = receiverVision.acceptSession(randomSessionId);
            }
            logger.info("=============El receptor de sesión y los clientes de receptor se han inicializado =================== sessionId {}", randomSessionId);
            IterableStream<ServiceBusReceivedMessage> messages = receiver.receiveMessages(1, Duration.ofSeconds(queueTimeOut));

            finalResponse = processMessages(messages, receiver, randomSessionId);

        } catch (Exception e) {
            logger.info("=============Error procesando colas de servicios : {}", e.toString());
            throw new BusinessException("0", InstantCardConstants.BUSINESS_EXC_RECEIEVE_MESSAGE, null);
        } finally {
            logger.info(" =============== Cerrando conexiones de sessionId {}", randomSessionId);

            if (!Objects.isNull(receiver)) {
                logger.info(" =============== Cerro conexion de receiver para sessionId {}", randomSessionId);
                receiver.close();
            }
            logger.info(" ===============Se Cerro las conexiones de sessionId {}", randomSessionId);
        }
        return finalResponse;
    }

    private String processMessages(IterableStream<ServiceBusReceivedMessage> messages, ServiceBusReceiverClient receiver, String randomSessionId) {
        String finalResponse = null;
        for (ServiceBusReceivedMessage message : messages) {
            logger.info("============= Inicio de la iteracion.============= sessionId {}", randomSessionId);
            finalResponse = processMessage(message);

            if (finalResponse != null) {
                logger.info("============= Completo la solicitud del mensaje.============= sessionId {}", randomSessionId);
                receiver.complete(message);
                break;
            } else {
                logger.info("============= abandono la solicitud del mensaje.============= sessionId {}", randomSessionId);
                receiver.abandon(message);
            }
            logger.info("============= fin de la iteracion.============= sessionId {}", randomSessionId);
        }
        return finalResponse;
    }

    private String processMessage(ServiceBusReceivedMessage message) {
        logger.info("============= Session: {}. Sequence #: {}. ", message.getSessionId(), message.getSequenceNumber());
        return Optional.ofNullable(message)
                .map(ServiceBusReceivedMessage::getBody)
                .map(BinaryData::toString)
                .orElse(null);
    }

}
