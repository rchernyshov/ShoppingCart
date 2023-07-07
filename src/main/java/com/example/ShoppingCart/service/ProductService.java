package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.ProductDTO;
import com.example.ShoppingCart.mapper.ProductMapper;
import com.example.ShoppingCart.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ShoppingCart.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllAvailableProducts() {
        log.info("Получение списка доступных продуктов");
        List<Product> products = productRepository.findByInStockTrue();
        List<ProductDTO> productDTOs = products.stream()
                .map(productMapper::mapToDTO)
                .collect(Collectors.toList());
        log.info("Получен список доступных продуктов: {}", productDTOs);
        return productDTOs;
    }
}


