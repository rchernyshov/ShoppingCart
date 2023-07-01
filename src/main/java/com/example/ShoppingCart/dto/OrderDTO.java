package com.example.ShoppingCart.dto;

import com.example.ShoppingCart.model.OrderStatus;
import com.example.ShoppingCart.model.PaymentMethod;
import lombok.Data;
import java.time.LocalDate;
@Data
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private Long customerId;
}
