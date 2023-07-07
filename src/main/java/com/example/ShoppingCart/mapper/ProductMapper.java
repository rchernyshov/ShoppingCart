package com.example.ShoppingCart.mapper;
import com.example.ShoppingCart.dto.ProductDTO;
import com.example.ShoppingCart.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO mapToDTO(Product product);

    Product mapToEntity(ProductDTO productDTO);
}
