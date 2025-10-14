package com.example.ordereventprocessor.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ordereventprocessor.model.OrderRequest;

@Service
public class OrderService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderRequest orderRequest) {
        kafkaTemplate.send("OrderEvent", orderRequest.toString());
        System.out.println("📤 Placed order: " + orderRequest);
    }
}