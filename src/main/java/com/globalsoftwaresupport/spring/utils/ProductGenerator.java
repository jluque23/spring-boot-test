package com.globalsoftwaresupport.spring.utils;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductsResponse;
import com.globalsoftwaresupport.spring.repositories.entity.Product;

public class ProductGenerator {

    public Product generateProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setCreatedAt(productRequest.getCreatedAt());
        product.setUpdatedAt(productRequest.getUpdatedAt());
        product.setActive(productRequest.isActive());

        return product;
    }

    public ProductsResponse generateProductsResponse(Product product) {

        return new ProductsResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.isActive());
    }
}
