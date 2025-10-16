package com.example.ordereventprocessor.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.model.OrderEntity;
@Service
public class KafkaOrderServiceProducer {
    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public KafkaOrderServiceProducer(KafkaTemplate<String, OrderEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEntity orderEntity) {
        kafkaTemplate.send("order-events", orderEntity);
        System.out.println("KAFKA PRODUCER - Sent message:");
        System.out.println("Order ID: " + orderEntity.getOrderId());
        System.out.println("Product: " + orderEntity.getProduct());
        System.out.println("Customer: " + orderEntity.getCustomerName());
        System.out.println("License Plate: " + orderEntity.getLicensePlate());
        System.out.println("Email: " + orderEntity.getCustomerEmail());
        System.out.println(" Phone: " + orderEntity.getCustomerPhoneNumber());
        System.out.println("========================================\n");
    }
    
}