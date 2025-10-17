package com.example.ordereventprocessor.model;

import java.util.List;
import jakarta.persistence.Column;
import  jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import  jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    
    @Column(unique = true, nullable = false)
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle")
    private List<OrderEntity> orders;


    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public List<OrderEntity> getOrders() {
        return orders;
    }
    
}
