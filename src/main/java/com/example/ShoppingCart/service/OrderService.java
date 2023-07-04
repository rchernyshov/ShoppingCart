package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.model.Order;
import com.example.ShoppingCart.model.OrderStatus;
import com.example.ShoppingCart.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Создание заказа: {}", orderDTO);
        // Преобразование OrderDTO в сущность Order
        Order order = new Order();

        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setPaymentMethod(orderDTO.getPaymentMethod());

        order = orderRepository.save(order); // Сохранение заказа в базе данных

        // Преобразование сущности Order в OrderDTO
        OrderDTO createdOrderDTO = new OrderDTO();
        createdOrderDTO.setId(order.getId());
        createdOrderDTO.setOrderDate(order.getOrderDate());
        createdOrderDTO.setOrderStatus(order.getOrderStatus());
        createdOrderDTO.setPaymentMethod(order.getPaymentMethod());
        createdOrderDTO.setCustomerId(order.getCustomer().getId());
        log.info("Заказ успешно создан: {}", createdOrderDTO);
        return createdOrderDTO;
    }

    public void deleteOrder(Long orderId) {
        log.info("Удаление заказа с идентификатором: {}", orderId);
        orderRepository.deleteById(orderId);
        log.info("Заказ успешно удален");
    }

    public OrderDTO updateOrderStatus(Long orderId, OrderStatus newStatus) {
        log.info("Обновление статуса заказа. Идентификатор заказа: {}, Новый статус: {}", orderId, newStatus);
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
            updatedOrderDTO.setCustomerId(order.getCustomer().getId());
            log.info("Статус заказа успешно обновлен: {}", updatedOrderDTO);
            return updatedOrderDTO;
        } else {
            throw new RuntimeException("Заказ не найден с ID: " + orderId);
        }
    }

    public OrderDTO getOrderById(Long orderId) {
        log.info("Получение информации о заказе по идентификатору: {}", orderId);

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Преобразование сущности Order в OrderDTO
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setOrderStatus(order.getOrderStatus());
            orderDTO.setPaymentMethod(order.getPaymentMethod());
            orderDTO.setCustomerId(order.getCustomer().getId());
            log.info("Информация о заказе успешно получена: {}", orderDTO);
            return orderDTO;
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }
}

