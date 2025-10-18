package com.example.ordereventprocessor.dto;

public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private Long vehicleId;
    private String vehicleLicensePlate;

    public OrderDTO(Long orderId, Long customerId, String customerName, String customerEmail,
            String customerPhoneNumber, Long vehicleId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.vehicleId = vehicleId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

}