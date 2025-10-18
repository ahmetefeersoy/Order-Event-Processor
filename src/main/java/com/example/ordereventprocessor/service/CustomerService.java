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
public class CustomerService {

    private final CustomerRepository _customerRepository;
    private final VehicleRepository _vehicleRepository;
    private final OrderRepository _orderRepository;
    private final KafkaOrderServiceProducer _kafkaProducer;

    public CustomerService(CustomerRepository customerRepository, VehicleRepository vehicleRepository,
            OrderRepository orderRepository, KafkaOrderServiceProducer kafkaProducer) {
        this._customerRepository = customerRepository;
        this._vehicleRepository = vehicleRepository;
        this._orderRepository = orderRepository;
        this._kafkaProducer = kafkaProducer;
    }

    public CustomerEntity createCustomer(OrderDTO orderDTO) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(orderDTO.getCustomerName());
        customer.setEmail(orderDTO.getCustomerEmail());
        customer.setPhoneNumber(orderDTO.getCustomerPhoneNumber());
        return _customerRepository.save(customer);
    }

}
