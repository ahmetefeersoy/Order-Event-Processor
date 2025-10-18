package com.example.ordereventprocessor.service;

import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.repository.CustomerRepository;
import com.example.ordereventprocessor.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.example.ordereventprocessor.dto.OrderDTO;
import com.example.ordereventprocessor.dto.VehicleDTO;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.model.CustomerEntity;
import com.example.ordereventprocessor.model.VehicleEntity;

@Service
public class VehicleService {

    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final VehicleRepository _vehicleRepository;
    private final KafkaOrderServiceProducer _kafkaProducer;

    public VehicleService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            KafkaOrderServiceProducer kafkaProducer) {
        this._orderRepository = orderRepository;
        this._customerRepository = customerRepository;
        this._vehicleRepository = vehicleRepository;
        this._kafkaProducer = kafkaProducer;
    }

    public VehicleEntity createVehicle(VehicleDTO vehicleDTO) {
        if (_vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate()).isPresent()) {
            throw new IllegalArgumentException("Vehicle with this license plate already exists");
        }
        if (vehicleDTO.getLicensePlate() == null || vehicleDTO.getLicensePlate().isEmpty()) {
            throw new IllegalArgumentException("License plate is required.");
        }
        if (!_vehicleRepository.isLicensePlateUnique(vehicleDTO.getLicensePlate())) {
            throw new IllegalArgumentException("License plate must be unique.");
        }

        if (vehicleDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());

        CustomerEntity customer = _customerRepository.findById(vehicleDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + vehicleDTO.getCustomerId()));
        vehicle.setCustomer(customer);


        _kafkaProducer.createEvent("VEHICLE_CREATED", vehicle);
        return _vehicleRepository.save(vehicle);
    }

}