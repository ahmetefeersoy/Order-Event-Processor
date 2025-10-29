package com.example.ordereventprocessor.eventhandler;

public interface EventHandler {

    public void handleEvent(Object payload);

    public String getEventType();

}
