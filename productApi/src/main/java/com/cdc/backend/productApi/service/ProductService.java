package com.cdc.backend.productApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.backend.shoppingClient.dto.ProductDTO;
import com.cdc.backend.shoppingClient.exception.CategoryNotFoundException;
import com.cdc.backend.shoppingClient.exception.ProductNotFoundException;
import com.cdc.backend.productApi.converter.DTOConverter;
import com.cdc.backend.productApi.model.Product;
import com.cdc.backend.productApi.repository.CategoryRepository;
import com.cdc.backend.productApi.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convert).toList();
    }

    public ProductDTO save(ProductDTO productDTO) {
        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = Product.convert(productDTO);
        product = productRepository.save(product);
        return DTOConverter.convert(product);
    }

    public Boolean delete(Long productId) {
        Optional<Product> Product = productRepository.findById(productId);
        if (Product.isPresent()) {
            productRepository.delete(Product.get());
            return true;
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Optional<Product> product = productRepository.findByProductIdentifier(productIdentifier);
        if (product.isPresent()) {
            return DTOConverter.convert(product.get());
        }
        throw new ProductNotFoundException();
    }

    public List<ProductDTO> getProductByCategoryId(long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(DTOConverter::convert).toList();
    }
}
