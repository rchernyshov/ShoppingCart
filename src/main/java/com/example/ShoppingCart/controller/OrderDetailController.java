package com.example.ShoppingCart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/order-details")
@Slf4j
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        log.info("Добавление деталей заказа: {}", orderDetailDTO);
        OrderDetailDTO createdOrderDetail = orderDetailService.addOrderDetail(orderDetailDTO);
        log.info("Детали заказа созданы: {}", createdOrderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long orderDetailId) {
        log.info("Удаление деталей заказа с ID: {}", orderDetailId);
        orderDetailService.deleteOrderDetail(orderDetailId);
        log.info("Детали заказа с ID {} удалены", orderDetailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        log.info("Получение деталей заказа по ID заказа: {}", orderId);
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        log.info("Детали заказа получены: {}", orderDetails);
        return ResponseEntity.ok(orderDetails);
    }
}


