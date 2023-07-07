package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.mapper.OrderMapper;
import com.example.ShoppingCart.model.Order;
import com.example.ShoppingCart.model.OrderDetail;
import com.example.ShoppingCart.model.OrderStatus;
import com.example.ShoppingCart.repository.OrderDetailRepository;
import com.example.ShoppingCart.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Создание заказа: {}", orderDTO);
        Order order = orderMapper.mapToEntity(orderDTO);

        order = orderRepository.save(order);

        OrderDTO createdOrderDTO = orderMapper.mapToDTO(order);
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

            OrderDTO updatedOrderDTO = orderMapper.mapToDTO(order);
            log.info("Статус заказа успешно обновлен: {}", updatedOrderDTO);
            return updatedOrderDTO;
        } else {
            throw new RuntimeException("Заказ не найден с ID: " + orderId);
        }
    }

    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            OrderDTO orderDTO = orderMapper.mapToDTO(order);
            return orderDTO;
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    public List<OrderDTO> getOrdersByDate(LocalDate date) {
        log.info("Получение заказов за дату: {}", date);
        List<Order> orders = orderRepository.findByOrderDate(date);

        List<OrderDTO> orderDTOs = orders.stream()
                .map(orderMapper::mapToDTO)
                .collect(Collectors.toList());

        log.info("Получено {} заказов за дату: {}", orderDTOs.size(), date);
        return orderDTOs;
    }

}


