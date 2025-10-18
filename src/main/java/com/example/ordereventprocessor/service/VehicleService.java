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
        VehicleEntity vehicle = _vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate())
                .orElseGet(() -> {
                    VehicleEntity v = new VehicleEntity();
                    v.setVehicleId(vehicleDTO.getVehicleId());
                    v.setLicensePlate(vehicleDTO.getLicensePlate());
                    return v;
                });

        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        return _vehicleRepository.save(vehicle);
    }
    

}