package com.example.ShoppingCart.config;
import com.example.ShoppingCart.mapper.OrderDetailMapper;
import com.example.ShoppingCart.mapper.OrderMapper;
import com.example.ShoppingCart.mapper.ProductMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class MapStructConfig {

    @Bean
    public OrderDetailMapper orderDetailMapper() {
        return OrderDetailMapper.INSTANCE;
    }

    @Bean
    public OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }

    @Bean
    public ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }
}
