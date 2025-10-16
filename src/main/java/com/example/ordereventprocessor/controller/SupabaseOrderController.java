package com.example.ordereventprocessor.controller;

import com.example.ordereventprocessor.model.OrderDTO;
import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supabase")
public class SupabaseOrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public SupabaseOrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }


    @PostMapping("/jpa/orders")
    public ResponseEntity<OrderEntity> createOrderJPA(@RequestBody OrderDTO orderDTO) {
        OrderEntity savedOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/jpa/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrdersJPA() {
        List<OrderEntity> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/jpa/orders/{id}")
    public ResponseEntity<OrderEntity> getOrderByIdJPA(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jpa/orders/license-plate/{licensePlate}")
    public ResponseEntity<List<OrderEntity>> getOrdersByLicensePlateJPA(@PathVariable String licensePlate) {
        List<OrderEntity> orders = orderRepository.findByLicensePlate(licensePlate);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/jpa/orders/customer/{name}")
    public ResponseEntity<List<OrderEntity>> getOrdersByCustomerJPA(@PathVariable String name) {
        List<OrderEntity> orders = orderRepository.findByCustomerName(name);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/jpa/orders/product/{product}")
    public ResponseEntity<List<OrderEntity>> getOrdersByProductJPA(@PathVariable String product) {
        List<OrderEntity> orders = orderRepository.findByProduct(product);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/jpa/orders/{id}")
    public ResponseEntity<Void> deleteOrderJPA(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    

}
