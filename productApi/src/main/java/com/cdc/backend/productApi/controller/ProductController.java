package com.cdc.backend.productApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdc.backend.shoppingClient.dto.ProductDTO;
import com.cdc.backend.productApi.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getMensagem() {
        return "Spring boot is working";
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/products/{productIdentifier}")
    public ProductDTO getFindByIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/products/category/{categoryId}")
    public List<ProductDTO> getUser(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/users/{id}")
    public Boolean removeProduct(@PathVariable Long id) {
        return productService.delete(id);
    }
}
