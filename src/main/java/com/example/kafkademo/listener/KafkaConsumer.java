package com.example.kafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "demo-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("📥 Received message: " + message);
    }
}
