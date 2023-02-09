package com.cdc.backend.shoppingApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cdc.backend.shoppingClient.dto.ProductDTO;
import com.cdc.backend.shoppingClient.exception.ProductNotFoundException;


@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081")
    private String productApiUrl;

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(productApiUrl + "/product/" + productIdentifier);

            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(builder.toString(), ProductDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }
    }
}
