package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.model.Order;
import com.example.ShoppingCart.model.OrderStatus;
import com.example.ShoppingCart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Логика создания заказа
        // Преобразование OrderDTO в сущность Order
        Order order = new Order();
        // Установка значений из OrderDTO в Order
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        // Сохранение заказа в базе данных
        order = orderRepository.save(order);
        // Преобразование сущности Order в OrderDTO
        OrderDTO createdOrderDTO = new OrderDTO();
        createdOrderDTO.setId(order.getId());
        createdOrderDTO.setOrderDate(order.getOrderDate());
        createdOrderDTO.setOrderStatus(order.getOrderStatus());
        createdOrderDTO.setPaymentMethod(order.getPaymentMethod());
        createdOrderDTO.setCustomerId(order.getCustomer().getId()); // Предположим, что у Order есть связь с Customer
        return createdOrderDTO;
    }

    public void deleteOrder(Long orderId) {
        // Логика удаления заказа
        orderRepository.deleteById(orderId);
    }

    public OrderDTO updateOrderStatus(Long orderId, OrderStatus newStatus) {
        // Логика изменения статуса заказа
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(newStatus);
            order = orderRepository.save(order);
            // Преобразование сущности Order в OrderDTO
            OrderDTO updatedOrderDTO = new OrderDTO();
            updatedOrderDTO.setId(order.getId());
            updatedOrderDTO.setOrderDate(order.getOrderDate());
            updatedOrderDTO.setOrderStatus(order.getOrderStatus());
            updatedOrderDTO.setPaymentMethod(order.getPaymentMethod());
            updatedOrderDTO.setCustomerId(order.getCustomer().getId()); // Предположим, что у Order есть связь с Customer
            return updatedOrderDTO;
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    public OrderDTO getOrderById(Long orderId) {
        // Логика получения информации о заказе по ID
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Преобразование сущности Order в OrderDTO
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setOrderStatus(order.getOrderStatus());
            orderDTO.setPaymentMethod(order.getPaymentMethod());
            orderDTO.setCustomerId(order.getCustomer().getId()); // Предположим, что у Order есть связь с Customer
            return orderDTO;
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }
}