package com.example.ordereventprocessor.service;
import com.example.ordereventprocessor.model.OrderDTO;
import com.example.ordereventprocessor.repository.OrderRepository;

import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.model.OrderEntity;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaOrderServiceProducer kafkaProducer;

    public OrderService(OrderRepository orderRepository, KafkaOrderServiceProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public OrderEntity createOrder(OrderDTO request) {
        OrderEntity order = new OrderEntity();
        order.setCustomerName(request.getCustomerName());
        order.setLicensePlate(request.getLicensePlate());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setCustomerPhoneNumber(request.getCustomerPhoneNumber());
        order.setProduct(request.getProduct());
        OrderEntity savedOrder = orderRepository.save(order);

        kafkaProducer.sendOrderEvent(savedOrder);

        return savedOrder;
    }
}
