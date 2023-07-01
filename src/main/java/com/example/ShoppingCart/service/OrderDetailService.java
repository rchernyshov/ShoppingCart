package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.model.Order;
import com.example.ShoppingCart.model.OrderDetail;
import com.example.ShoppingCart.model.Product;
import org.springframework.stereotype.Service;
import com.example.ShoppingCart.repository.OrderDetailRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetailDTO addOrderDetail(OrderDetailDTO orderDetailDTO) {
        // Преобразование OrderDetailDTO в сущность OrderDetail
        OrderDetail orderDetail = mapToEntity(orderDetailDTO);

        // Сохранение позиции заказа в базе данных
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);

        // Преобразование сохраненной сущности в OrderDetailDTO и возврат
        return mapToDTO(savedOrderDetail);
    }

    public void deleteOrderDetail(Long orderDetailId) {
        // Удаление позиции заказа из базы данных по идентификатору
        orderDetailRepository.deleteById(orderDetailId);
    }

    public List<OrderDetailDTO> getOrderDetailsByOrderId(Long orderId) {
        // Получение всех позиций заказа по указанному orderId
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(orderId);

        // Преобразование списка сущностей OrderDetail в список OrderDetailDTO
        return orderDetails.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private OrderDetailDTO mapToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        // Преобразование полей сущности в поля DTO
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setOrderId(orderDetail.getOrder().getId()); // Получение идентификатора заказа
        orderDetailDTO.setProductId(orderDetail.getProduct().getId()); // Получение идентификатора продукта
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

