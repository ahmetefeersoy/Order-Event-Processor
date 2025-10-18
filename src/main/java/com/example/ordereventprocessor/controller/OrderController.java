package com.example.ordereventprocessor.controller;

import com.example.ordereventprocessor.dto.OrderDTO;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.service.OrderService;

import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supabase")
public class OrderController {

    private final OrderRepository _orderRepository;
    private final OrderService _orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this._orderRepository = orderRepository;
        this._orderService = orderService;
    }

    // Her şey DB'ye kaydedildikten sonra kafkaya event atmalı. !!!!!!!!!!!!!!!!
    @PostMapping("/jpa/orders")
    public ResponseEntity<OrderEntity> createOrderJPA(@RequestBody OrderDTO orderDTO) {
        OrderEntity savedOrder = _orderService.createOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/jpa/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrdersJPA() {
        List<OrderEntity> orders = _orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/jpa/orders/{id}")
    public ResponseEntity<OrderEntity> getOrderByIdJPA(@PathVariable Long id) {
        return _orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jpa/orders/customer/{name}")
    public ResponseEntity<List<OrderEntity>> getOrdersByCustomerJPA(@PathVariable String name) {
        List<OrderEntity> orders = _orderRepository.findByCustomerName(name);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/jpa/orders/product/{product}")
    public ResponseEntity<List<OrderEntity>> getOrdersByProductJPA(@PathVariable String product) {
        List<OrderEntity> orders = _orderRepository.findByProduct(product);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/jpa/orders/{id}")
    public ResponseEntity<Void> deleteOrderJPA(@PathVariable Long id) {
        if (_orderRepository.existsById(id)) {
            _orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    

}
