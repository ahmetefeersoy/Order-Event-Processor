package com.example.ordereventprocessor.repository;

import com.example.ordereventprocessor.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    
    List<OrderEntity> findByCustomerName(String customerName);

    List<OrderEntity> findByProduct(String product);
    
    List<OrderEntity> findByCustomerNameContainingIgnoreCase(String customerName);

    List<OrderEntity> findByLicensePlate(String licensePlate);

}
