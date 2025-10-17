package com.example.ordereventprocessor.dto;

public class NotificationDTO {
    private Long notificationId;
    private String message;
    private String recipientEmail;
    private Long customerId;
    private String customerName;

    public NotificationDTO() {
    }

    public NotificationDTO(Long notificationId, String message, String recipientEmail, Long customerId) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipientEmail = recipientEmail;
        this.customerId = customerId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
