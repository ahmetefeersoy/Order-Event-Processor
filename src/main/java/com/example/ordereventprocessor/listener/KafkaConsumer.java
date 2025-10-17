package com.example.ordereventprocessor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.example.ordereventprocessor.model.OrderEntity;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "order-events", groupId = "group_id")
    public void consume(OrderEntity message) {
        logger.info("KAFKA CONSUMER - Received message:");
        logger.info("Order ID: {}", message.getOrderId());
        logger.info("Customer: {}", message.getCustomer().getName());
        logger.info("Email: {}", message.getCustomer().getEmail());
        logger.info("Phone: {}", message.getCustomer().getPhoneNumber());
        logger.info("Vehicle License Plate: {}", message.getVehicle().getLicensePlate());
        logger.info("========================================");
    }
}
