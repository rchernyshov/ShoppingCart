package com.example.ShoppingCart.scheduler;

import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.service.OrderDetailService;
import com.example.ShoppingCart.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderScheduler {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private double thresholdAmount;
    private Environment environment;

    public OrderScheduler(OrderService orderService, OrderDetailService orderDetailService, Environment environment) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.environment = environment;
    }

    @PostConstruct
    public void setThresholdAmount() {
        this.thresholdAmount = Double.parseDouble(environment.getProperty("order.threshold.amount"));
    }

    @Scheduled(cron = "${order.scheduler.cron}")
    public void processDailyOrders() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<OrderDTO> orders = orderService.getOrdersByDate(yesterday);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDTO order : orders) {
            List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(order.getId());
            for (OrderDetailDTO orderDetail : orderDetails) {
                BigDecimal detailAmount = orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity()));
                totalAmount = totalAmount.add(detailAmount);
            }
        }

        if (totalAmount.compareTo(BigDecimal.valueOf(thresholdAmount)) > 0) {
            log.info("Заказы за последний день с общей суммой, превышающей {}: {}", thresholdAmount, orders);
        }
    }
}
