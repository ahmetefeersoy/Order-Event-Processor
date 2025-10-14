package com.example.kafkademo.controller;

import com.example.kafkademo.service.OrderService;
import com.example.kafkademo.model.OrderRequest;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final List<OrderRequest> orders = new ArrayList<>();
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place_order")
    public String placeOrder(@RequestBody OrderRequest orderDetails) {
        orders.add(orderDetails);
        orderService.placeOrder(orderDetails);
        return "Order created successfully";
    }
    @GetMapping("/orders")
    public List<OrderRequest> getOrders() {
        return orders;
    }

}
