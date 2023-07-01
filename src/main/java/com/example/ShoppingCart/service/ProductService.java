package com.example.ShoppingCart.service;

import com.example.ShoppingCart.dto.ProductDTO;
import com.example.ShoppingCart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ShoppingCart.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllAvailableProducts() {
        List<Product> products = productRepository.findByInStockTrue();
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }
}
