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

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final KafkaOrderServiceProducer kafkaProducer;

    public OrderService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            KafkaOrderServiceProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public OrderEntity createOrder(OrderDTO request) {
        CustomerEntity customer = customerRepository.findByEmail(request.getCustomerEmail())
                .orElseGet(() -> {
                    CustomerEntity newCustomer = new CustomerEntity();
                    newCustomer.setName(request.getCustomerName());
                    newCustomer.setEmail(request.getCustomerEmail());
                    newCustomer.setPhoneNumber(request.getCustomerPhoneNumber());
                    return customerRepository.save(newCustomer);
                });

        VehicleEntity vehicle = vehicleRepository.findByLicensePlate(request.getLicensePlate())
                .orElseGet(() -> {
                    VehicleEntity newVehicle = new VehicleEntity();
                    newVehicle.setLicensePlate(request.getLicensePlate());
                    return vehicleRepository.save(newVehicle);
                });

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setVehicle(vehicle);
        OrderEntity savedOrder = orderRepository.save(order);

        kafkaProducer.sendOrderEvent(savedOrder);

        return savedOrder;
    }
}
