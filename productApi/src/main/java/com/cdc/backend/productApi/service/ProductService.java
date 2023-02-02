package com.cdc.backend.productApi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.backend.productApi.dto.ProductDTO;
import com.cdc.backend.productApi.model.Product;
import com.cdc.backend.productApi.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductDTO::convert).toList();
	}

	public ProductDTO save(ProductDTO productDTO) {
		Product product = Product.convert(productDTO);
		product = productRepository.save(product);
		return ProductDTO.convert(product);
	}

	public Boolean delete(Long productId) {
		Optional<Product> Product = productRepository.findById(productId);
		if (Product.isPresent()) {
			productRepository.delete(Product.get());
			return true;
		}
		return false;
	}

	public ProductDTO findByProductIdentifier(String productIdentifier) {
		Optional<Product> product = productRepository.findByProductIdentifier(productIdentifier);
		if (product.isPresent()) {
			return ProductDTO.convert(product.get());
		}
		return null;
	}

	public List<ProductDTO> getProductByCategoryId(long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(ProductDTO::convert).toList();
	}
}
