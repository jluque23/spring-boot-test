package com.globalsoftwaresupport.spring.services.interfaces;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductsResponse;
import com.globalsoftwaresupport.spring.repositories.entity.Product;

import java.util.List;

public interface IProductService {

    List<ProductsResponse> findAllProducts();

    void saveProduct(ProductRequest productRequest);

    ProductsResponse findProductById(Long id);
}
