package com.example.ordereventprocessor.dto;

import java.util.List;

public class VehicleDTO {
    private Long vehicleId;
    private String licensePlate;
    private List<Long> orderIds;

    public VehicleDTO(Long vehicleId, String licensePlate, List<Long> orderIds) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.orderIds = orderIds;
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

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
