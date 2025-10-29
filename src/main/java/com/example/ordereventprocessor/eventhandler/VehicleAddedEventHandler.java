package com.example.ordereventprocessor.eventhandler;

import com.example.ordereventprocessor.model.VehicleEntity;

public class VehicleAddedEventHandler implements EventHandler {

    public void handleEvent(Object payload) {
        VehicleEntity vehicle = (VehicleEntity) payload;
        System.out.println("Handling VEHICLE_ADDED event for Vehicle ID: " + vehicle.getVehicleId());

        // business logic
    }

    public String getEventType() {
        return "VEHICLE_ADDED";
    }
}

        