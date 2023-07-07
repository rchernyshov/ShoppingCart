package com.example.ShoppingCart.mapper;

import com.example.ShoppingCart.dto.OrderDTO;
import com.example.ShoppingCart.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "order.customer.id", target = "customerId")
    OrderDTO mapToDTO(Order order);

    @Mapping(source = "customerId", target = "customer.id")
    Order mapToEntity(OrderDTO orderDTO);
}

