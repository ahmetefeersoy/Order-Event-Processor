package com.example.ordereventprocessor.eventhandler;

import com.example.ordereventprocessor.model.CustomerEntity;

public class CustomerUpdatedEventHandler implements EventHandler {

    public void handleEvent(Object payload) {
        CustomerEntity customer = (CustomerEntity) payload;
        System.out.println("Handling CUSTOMER_UPDATED event for Customer ID: " + customer.getCustomerId());
        // business logic
    }

    public String getEventType() {
        return "CUSTOMER_UPDATED";
    }
}
