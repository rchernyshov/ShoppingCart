package com.example.ShoppingCart.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ShoppingCart.dto.ProductDTO;
import com.example.ShoppingCart.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Получение списка всех продуктов");
        List<ProductDTO> products = productService.getAllAvailableProducts();
        log.info("Получено {} продуктов", products.size());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

