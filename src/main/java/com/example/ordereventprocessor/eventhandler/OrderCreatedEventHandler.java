package com.example.ordereventprocessor.eventhandler;
import com.example.ordereventprocessor.model.OrderEntity;

public class OrderCreatedEventHandler implements EventHandler {

    @Override
    public void handleEvent(Object payload) {
        OrderEntity order = (OrderEntity) payload;
        System.out.println("Handling ORDER_CREATED event for Order ID: " + order.getOrderId());
        
        // business logic
    }

    @Override
    public String getEventType() {
        return "ORDER_CREATED";
    }

}
    