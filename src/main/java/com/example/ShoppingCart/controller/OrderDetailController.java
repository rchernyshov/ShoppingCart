package com.example.ShoppingCart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO createdOrderDetail = orderDetailService.addOrderDetail(orderDetailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long orderDetailId) {
        orderDetailService.deleteOrderDetail(orderDetailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}

