package com.example.ordereventprocessor.dto;

public class VehicleDTO {
    private Long vehicleId;
    private String licensePlate;
    private Long customerId;

   
    public VehicleDTO(Long vehicleId, String licensePlate, Long customerId) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.customerId = customerId;
    }

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}