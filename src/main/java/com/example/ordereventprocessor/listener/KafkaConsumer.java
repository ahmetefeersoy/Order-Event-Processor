package com.example.ordereventprocessor.listener;

import com.example.ordereventprocessor.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "events-topic", groupId = "group_id")
    public void handleEvent(Event event) {
        logger.info("Received event from Kafka: {}", event.getEventType());

        switch (event.getEventType()) {
            case "ORDER_CREATED":
                OrderEntity order = (OrderEntity) event.getPayload();
                logger.info("Processing ORDER_CREATED event for Order ID: {}", order.getOrderId());
                // business logic
                break;

            case "VEHICLE_ADDED":
                // business logic
                logger.info("Processing VEHICLE_ADDED event");
                break;

            case "CUSTOMER_UPDATED":
                // business logic
                logger.info("Processing CUSTOMER_UPDATED event");
                break;

            case "NOTIFICATION_CREATED":
                // business logic
                logger.info("Processing NOTIFICATION_CREATED event");
                break;
                
            default:
                logger.warn("Unhandled event type: {}", event.getEventType());
        }
    }

}