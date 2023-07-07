package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.mapper.OrderDetailMapper;
import com.example.ShoppingCart.model.Order;
import com.example.ShoppingCart.model.OrderDetail;
import com.example.ShoppingCart.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.ShoppingCart.repository.OrderDetailRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    public OrderDetailDTO addOrderDetail(OrderDetailDTO orderDetailDTO) {
        log.info("Добавление позиции заказа: {}", orderDetailDTO);
        OrderDetail orderDetail = orderDetailMapper.mapToEntity(orderDetailDTO);

        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);

        OrderDetailDTO savedOrderDetailDTO = orderDetailMapper.mapToDTO(savedOrderDetail);
        log.info("Позиция заказа успешно добавлена: {}", savedOrderDetailDTO);
        return savedOrderDetailDTO;
    }

    public void deleteOrderDetail(Long orderDetailId) {
        log.info("Удаление позиции заказа с идентификатором: {}", orderDetailId);
        orderDetailRepository.deleteById(orderDetailId);
        log.info("Позиция заказа успешно удалена");
    }

    public List<OrderDetailDTO> getOrderDetailsByOrderId(Long orderId) {
        log.info("Получение позиций заказа по идентификатору заказа: {}", orderId);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(orderId);

        // Преобразование списка сущностей OrderDetail в список OrderDetailDTO
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream()
                .map(orderDetailMapper::mapToDTO)
                .collect(Collectors.toList());

        log.info("Получено {} позиций заказа", orderDetailDTOs.size());
        return orderDetailDTOs;
    }
}


