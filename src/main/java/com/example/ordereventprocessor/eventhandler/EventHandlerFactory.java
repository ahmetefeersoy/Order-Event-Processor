package com.example.ordereventprocessor.eventhandler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandlerFactory {

    private final Map<String, EventHandler> handlers = new HashMap<>();

    public EventHandlerFactory(
        OrderCreatedEventHandler orderHandler,
        VehicleAddedEventHandler vehicleHandler,
        CustomerUpdatedEventHandler customerHandler,
        NotificationCreatedEventHandler notificationHandler
    ) {
        handlers.put("ORDER_CREATED", orderHandler);
        handlers.put("VEHICLE_ADDED", vehicleHandler);
        handlers.put("CUSTOMER_UPDATED", customerHandler);
        handlers.put("NOTIFICATION_CREATED", notificationHandler);
    }

    public EventHandler getHandler(String eventType) {
        return handlers.get(eventType);
    }
}
