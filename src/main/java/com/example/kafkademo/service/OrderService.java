package com.example.kafkademo.service;

import com.example.kafkademo.model.OrderRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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