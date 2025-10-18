package com.example.ordereventprocessor.service;

import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.repository.CustomerRepository;
import com.example.ordereventprocessor.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.dto.OrderDTO;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.model.CustomerEntity;
import com.example.ordereventprocessor.model.VehicleEntity;

@Service
public class OrderService {

    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final VehicleRepository _vehicleRepository;
    private final KafkaOrderServiceProducer _kafkaProducer;

    public OrderService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            KafkaOrderServiceProducer kafkaProducer) {
        this._orderRepository = orderRepository;
        this._customerRepository = customerRepository;
        this._vehicleRepository = vehicleRepository;
        this._kafkaProducer = kafkaProducer;
    }

    public OrderEntity createOrder(OrderDTO request) {
        CustomerEntity customer = _customerRepository.findByEmail(request.getCustomerEmail())
                .orElseGet(() -> {
                    CustomerEntity newCustomer = new CustomerEntity();
                    newCustomer.setName(request.getCustomerName());
                    newCustomer.setEmail(request.getCustomerEmail());
                    newCustomer.setPhoneNumber(request.getCustomerPhoneNumber());
                    return _customerRepository.save(newCustomer);
                });

        VehicleEntity vehicle = _vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + request.getVehicleId()));

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setVehicle(vehicle);
        OrderEntity savedOrder = _orderRepository.save(order);

        _kafkaProducer.createEvent("ORDER_CREATED", savedOrder);

        return savedOrder;
    }
}
