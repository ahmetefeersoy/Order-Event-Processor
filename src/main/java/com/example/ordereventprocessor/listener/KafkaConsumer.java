package com.example.ordereventprocessor.listener;

import com.example.ordereventprocessor.eventhandler.*;
import com.example.ordereventprocessor.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final EventHandlerFactory eventHandlerFactory;

    public KafkaConsumer(EventHandlerFactory eventHandlerFactory) {
        this.eventHandlerFactory = eventHandlerFactory;
    }

    @KafkaListener(topics = "events-topic", groupId = "group_id")
    public void handleEvent(Event event) {
        logger.info("Received event from Kafka: {}", event.getEventType());

        EventHandler handler = eventHandlerFactory.getHandler(event.getEventType());

        if (handler != null) {
            handler.handleEvent(event);
            logger.info("Finished processing event: {}", event.getEventType());
        } else {
            logger.warn("No handler found for event type: {}", event.getEventType());
        }
    }
}
