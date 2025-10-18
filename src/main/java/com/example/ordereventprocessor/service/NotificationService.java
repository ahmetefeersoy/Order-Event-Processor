package com.example.ordereventprocessor.service;
import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.repository.CustomerRepository;
import com.example.ordereventprocessor.repository.VehicleRepository;
import com.example.ordereventprocessor.repository.NotificationRepository;

import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.dto.OrderDTO;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.model.CustomerEntity;
import com.example.ordereventprocessor.model.VehicleEntity;
import com.example.ordereventprocessor.model.NotificationEntity;

@Service
public class NotificationService {
    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final VehicleRepository _vehicleRepository;
    private final KafkaOrderServiceProducer _kafkaProducer;
    private final NotificationRepository _notificationRepository;

    public NotificationService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            KafkaOrderServiceProducer kafkaProducer,
            NotificationRepository notificationRepository) {
        this._orderRepository = orderRepository;
        this._customerRepository = customerRepository;
        this._vehicleRepository = vehicleRepository;
        this._kafkaProducer = kafkaProducer;
        this._notificationRepository = notificationRepository;
    }

    public NotificationEntity createNotification(OrderDTO orderDTO) {
        NotificationEntity notification = new NotificationEntity();
        notification.setTimestamp(System.currentTimeMillis());
        notification.setMessage("New order created: " + orderDTO.getOrderId());
        notification.setRecipientEmail(orderDTO.getCustomerEmail());
        _notificationRepository.save(notification);
        _kafkaProducer.createEvent("NOTIFICATION_CREATED", notification);
        
        return notification;
    }

}
