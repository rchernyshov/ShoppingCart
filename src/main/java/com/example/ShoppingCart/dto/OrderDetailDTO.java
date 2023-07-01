package com.example.ShoppingCart.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private BigDecimal price;
    private int quantity;

    public OrderDetailDTO() {
    }

    // Конструкторы, геттеры и сеттеры
    // ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

