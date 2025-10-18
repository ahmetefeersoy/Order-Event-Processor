package com.example.ordereventprocessor.service;

import javax.management.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ordereventprocessor.model.NotificationEntity;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.model.Event;

@Service
public class KafkaOrderServiceProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrderServiceProducer.class);
    private final KafkaTemplate<String, Event> kafkaTemplate;
    public KafkaOrderServiceProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createEvent(String eventType, Object payload) {
        Event event = new Event(eventType, payload);
        kafkaTemplate.send("events-topic", event);
        logger.info("Sent event to Kafka: {}", event);
    }

}