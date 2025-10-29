package com.example.ordereventprocessor.eventhandler;

public class NotificationCreatedEventHandler implements EventHandler {

    public void handleEvent(Object payload) {
        System.out.println("Handling NOTIFICATION_CREATED event");
        
        // business logic
    }

    public String getEventType() {
        return "NOTIFICATION_CREATED";
    }

}
