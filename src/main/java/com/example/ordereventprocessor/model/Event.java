package com.example.ordereventprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String eventType; //"ORDER_CREATED", "VEHICLE_ADDED", "CUSTOMER_UPDATED" , "NOTIFICATION_CREATED" etc.
    private Object payload;
}