package com.example.ordereventprocessor.controller;

import com.example.ordereventprocessor.model.OrderEntity;
import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.service.SupabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supabase")
public class SupabaseOrderController {

    private final OrderRepository orderRepository;
    private final SupabaseService supabaseService;

    public SupabaseOrderController(OrderRepository orderRepository, SupabaseService supabaseService) {
        this.orderRepository = orderRepository;
        this.supabaseService = supabaseService;
    }


    @PostMapping("/jpa/orders")
    public ResponseEntity<OrderEntity> createOrderJPA(@RequestBody OrderEntity order) {
        OrderEntity savedOrder = orderRepository.save(order);
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

    @GetMapping("/jpa/orders/customer/{name}")
    public ResponseEntity<List<OrderEntity>> getOrdersByCustomerJPA(@PathVariable String name) {
        List<OrderEntity> orders = orderRepository.findByCustomerName(name);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/jpa/orders/{id}")
    public ResponseEntity<OrderEntity> updateOrderJPA(@PathVariable Long id, @RequestBody OrderEntity order) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCustomerName(order.getCustomerName());
                    existingOrder.setProduct(order.getProduct());
                    existingOrder.setQuantity(order.getQuantity());
                    OrderEntity updatedOrder = orderRepository.save(existingOrder);
                    return ResponseEntity.ok(updatedOrder);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jpa/orders/{id}")
    public ResponseEntity<Void> deleteOrderJPA(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/rest/orders")
    public ResponseEntity<String> createOrderREST(@RequestBody OrderEntity order) {
        return supabaseService.insertData("orders", order);
    }

    @GetMapping("/rest/orders")
    public ResponseEntity<String> getAllOrdersREST() {
        return supabaseService.queryData("orders", "");
    }

    @GetMapping("/rest/orders/{id}")
    public ResponseEntity<String> getOrderByIdREST(@PathVariable Long id) {
        return supabaseService.queryData("orders", "?id=eq." + id);
    }

    @GetMapping("/rest/orders/customer/{name}")
    public ResponseEntity<String> getOrdersByCustomerREST(@PathVariable String name) {
        return supabaseService.queryData("orders", "?customer_name=eq." + name);
    }

    @PutMapping("/rest/orders/{id}")
    public ResponseEntity<String> updateOrderREST(@PathVariable Long id, @RequestBody OrderEntity order) {
        return supabaseService.updateData("orders", "?id=eq." + id, order);
    }

    @DeleteMapping("/rest/orders/{id}")
    public ResponseEntity<String> deleteOrderREST(@PathVariable Long id) {
        return supabaseService.deleteData("orders", "?id=eq." + id);
    }
}
