package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDetailDTO;
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

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetailDTO addOrderDetail(OrderDetailDTO orderDetailDTO) {
        log.info("Добавление позиции заказа: {}", orderDetailDTO);
        OrderDetail orderDetail = mapToEntity(orderDetailDTO);  //Преобразование OrderDetailDTO в сущность OrderDetail


        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail); //Сохранение позиции заказа в базе данных


        OrderDetailDTO savedOrderDetailDTO = mapToDTO(savedOrderDetail);  //Преобразование сохраненной сущности в OrderDetailDTO
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

        //Преобразование списка сущностей OrderDetail в список OrderDetailDTO
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        log.info("Получено {} позиций заказа", orderDetailDTOs.size());
        return orderDetailDTOs;
    }

    private OrderDetailDTO mapToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        // Преобразование полей сущности в поля DTO
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setOrderId(orderDetail.getOrder().getId());
        orderDetailDTO.setProductId(orderDetail.getProduct().getId());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        return orderDetailDTO;
    }

    private OrderDetail mapToEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        // Преобразование полей DTO в поля сущности
        orderDetail.setId(orderDetailDTO.getId());

        Order order = new Order();
        order.setId(orderDetailDTO.getOrderId());
        orderDetail.setOrder(order);

        Product product = new Product();
        product.setId(orderDetailDTO.getProductId());
        orderDetail.setProduct(product);

        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        return orderDetail;
    }
}


