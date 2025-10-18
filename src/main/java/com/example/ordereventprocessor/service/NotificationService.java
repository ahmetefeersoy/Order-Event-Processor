package com.example.ordereventprocessor.service;

import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.repository.CustomerRepository;
import com.example.ordereventprocessor.repository.VehicleRepository;


import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.dto.OrderDTO;

import com.example.ordereventprocessor.model.NotificationEntity;

@Service
public class NotificationService {
    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final VehicleRepository _vehicleRepository;
    private final KafkaOrderServiceProducer _kafkaProducer;

    public NotificationService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            KafkaOrderServiceProducer kafkaProducer) {
        this._orderRepository = orderRepository;
        this._customerRepository = customerRepository;
        this._vehicleRepository = vehicleRepository;
        this._kafkaProducer = kafkaProducer;
    }

    public NotificationEntity createNotification(OrderDTO orderDTO) {
        NotificationEntity notification = new NotificationEntity();
        notification.setTimestamp(System.currentTimeMillis());
        notification.setMessage("New order created: " + orderDTO.getOrderId());
        notification.setRecipientEmail(orderDTO.getCustomerEmail());
       
        return notification;
    }

}
