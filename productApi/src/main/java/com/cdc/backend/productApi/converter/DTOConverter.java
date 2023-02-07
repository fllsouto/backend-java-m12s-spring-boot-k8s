package com.cdc.backend.productApi.converter;


import com.cdc.backend.productApi.model.Category;
import com.cdc.backend.productApi.model.Product;
import com.cdc.backend.shoppingClient.dto.CategoryDTO;
import com.cdc.backend.shoppingClient.dto.ProductDTO;

public class DTOConverter {

    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setDescricao(product.getDescricao());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        if (product.getCategory() != null) {
            productDTO.setCategory(DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
}
