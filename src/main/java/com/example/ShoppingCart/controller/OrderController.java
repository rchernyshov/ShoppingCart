package com.example.ShoppingCart.controller;

import com.example.ShoppingCart.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.service.OrderService;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService; // Зависимость от сервиса заказов

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("Создание заказа: {}", orderDTO);
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        log.info("Заказ создан: {}", createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        log.info("Удаление заказа с ID: {}", orderId);
        orderService.deleteOrder(orderId);
        log.info("Заказ с ID {} удален", orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus newStatus) {
        log.info("Обновление статуса заказа с ID {}: Новый статус - {}", orderId, newStatus);
        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, newStatus);
        log.info("Статус заказа с ID {} обновлен: {}", orderId, updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        log.info("Получение заказа с ID: {}", orderId);
        OrderDTO order = orderService.getOrderById(orderId);
        log.info("Заказ получен: {}", order);
        return ResponseEntity.ok(order);
    }
}



