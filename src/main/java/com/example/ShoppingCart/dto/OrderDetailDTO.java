package com.example.ShoppingCart.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private BigDecimal price;
    private int quantity;
}

