package com.orchestration.instantcard.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.orchestration.instantcard.queue.ServiceBusReceiverClientWrapper;
import com.orchestration.instantcard.queue.ServiceBusSenderClientWrapper;
import com.orchestration.instantcard.queue.impl.ServiceBusReceiverClientWrapperImpl;
import com.orchestration.instantcard.queue.impl.ServiceBusSenderClientWrapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusConfig {
    public static final String INFO_SERVICE_BUS_CLIENT_BUILDER = "================Obtener ServiceBusClientBuilder y realizar ";
    @Value("${spring.jms.servicebus.connection-string}")
    private String connectionString;
    @Value("${service-id.ibs-sendername}")
    private String destinationNameIbs;

    @Value("${service-id.ibs-receivername}")
    private String responseNameIbs;

    @Value("${service-id.vision-sendername}")
    private String destinationNameVision;

    @Value("${service-id.vision-receivername}")
    private String responseNameVision;

    private static final Logger logger = LoggerFactory.getLogger(ServiceBusConfig.class);

    @Bean
    public ServiceBusClientBuilder builder() {
        logger.info("================Creando sesion de tipo ServiceBusClientBuilder " +
                "sobre connectionString [{}]", connectionString);
        return new ServiceBusClientBuilder().connectionString(connectionString);
    }
    @Bean
    public ServiceBusSenderClientWrapper senderIbs() {
        logger.info(INFO_SERVICE_BUS_CLIENT_BUILDER +
                "sender de ServiceBusSenderClient sobre connectionString [{}]," +
                " hacia la cola [{}]", connectionString, destinationNameIbs);
        var serviceBusSenderClient = createSender(destinationNameIbs);
        logger.info("senderIbs init identifier ,{}",serviceBusSenderClient.getIdentifier() );
        logger.info("senderIbs init hashcode ,{}",serviceBusSenderClient.hashCode() );
        return new ServiceBusSenderClientWrapperImpl(serviceBusSenderClient);

    }
    @Bean
    public ServiceBusReceiverClientWrapper receiverIbs() {
        logger.info(INFO_SERVICE_BUS_CLIENT_BUILDER +
                "receiver de ServiceBusSessionReceiverClient " +
                "sobre connectionString [{}], hacia la cola [{}]", connectionString, responseNameIbs);
        var serviceBusSessionReceiverClient = createReceiver(responseNameIbs);
        logger.info("receiverIbs init hashCode ,{}",serviceBusSessionReceiverClient.hashCode() );
        return new ServiceBusReceiverClientWrapperImpl(serviceBusSessionReceiverClient);
    }
    @Bean
    public ServiceBusSenderClientWrapper senderVision() {
        logger.info(INFO_SERVICE_BUS_CLIENT_BUILDER +
                "sender de ServiceBusSenderClient sobre connectionString [{}]," +
                " hacia la cola [{}]", connectionString, destinationNameVision);
        var serviceBusSenderClient = createSender(destinationNameVision);
        logger.info("senderVision init identifier ,{}",serviceBusSenderClient.getIdentifier() );
        logger.info("senderVision init hashcode ,{}",serviceBusSenderClient.hashCode() );
        return new ServiceBusSenderClientWrapperImpl(serviceBusSenderClient);
    }
    @Bean
    public ServiceBusReceiverClientWrapper receiverVision() {
        logger.info(INFO_SERVICE_BUS_CLIENT_BUILDER +
                "receiver de ServiceBusSessionReceiverClient " +
                "sobre connectionString [{}], hacia la cola [{}]", connectionString, responseNameVision);
        var serviceBusSessionReceiverClient =createReceiver(responseNameVision);
        logger.info("receiverVision init hashCode ,{}",serviceBusSessionReceiverClient.hashCode() );
        return new ServiceBusReceiverClientWrapperImpl(serviceBusSessionReceiverClient);

    }
    private ServiceBusSenderClient createSender(String destinationName) {
        return builder()
                .sender()
                .queueName(destinationName)
                .buildClient();
    }
    private ServiceBusSessionReceiverClient createReceiver(String responseName) {
        return builder()
                .sessionReceiver()
                .queueName(responseName)
                .buildClient();
    }

}
