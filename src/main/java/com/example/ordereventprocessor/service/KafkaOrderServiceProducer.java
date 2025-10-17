package com.example.ordereventprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.model.OrderEntity;

@Service
public class KafkaOrderServiceProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrderServiceProducer.class);
    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public KafkaOrderServiceProducer(KafkaTemplate<String, OrderEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEntity orderEntity) {
        kafkaTemplate.send("order-events", orderEntity);
        logger.info("KAFKA PRODUCER - Sent message:");
        logger.info("Order ID: {}", orderEntity.getOrderId());
        logger.info("Customer: {}", orderEntity.getCustomer().getName());
        logger.info("Email: {}", orderEntity.getCustomer().getEmail());
        logger.info("Phone: {}", orderEntity.getCustomer().getPhoneNumber());
        logger.info("Vehicle License Plate: {}", orderEntity.getVehicle().getLicensePlate());
        logger.info("========================================");
    }
    
}