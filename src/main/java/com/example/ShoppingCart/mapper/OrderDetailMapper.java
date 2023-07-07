package com.example.ShoppingCart.mapper;
import com.example.ShoppingCart.dto.OrderDetailDTO;
import com.example.ShoppingCart.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    OrderDetailDTO mapToDTO(OrderDetail orderDetail);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    OrderDetail mapToEntity(OrderDetailDTO orderDetailDTO);
}




