package com.example.ordereventprocessor.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.example.ordereventprocessor.model.OrderEntity;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "order-events", groupId = "group_id")
    public void consume(OrderEntity message) {
        System.out.println("KAFKA CONSUMER - Received message:");
        System.out.println("Order ID: " + message.getOrderId());
        System.out.println("Product: " + message.getProduct());
        System.out.println("Customer: " + message.getCustomerName());
        System.out.println("License Plate: " + message.getLicensePlate());
        System.out.println("Email: " + message.getCustomerEmail());
        System.out.println("Phone: " + message.getCustomerPhoneNumber());
        System.out.println("========================================\n");
    }
}
