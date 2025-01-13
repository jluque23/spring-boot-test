package com.globalsoftwaresupport.spring.utils;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductResponse;
import com.globalsoftwaresupport.spring.repositories.entity.Product;
import com.globalsoftwaresupport.spring.services.ProductServiceImpl;

import java.util.Date;

public class ProductGenerator {

    public Product generateProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        ProductServiceImpl.productRequestToProduct(productRequest, product);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        return product;
    }

    public ProductResponse generateProductsResponse(Product product) {

        return new ProductResponse(product.getId(),
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
