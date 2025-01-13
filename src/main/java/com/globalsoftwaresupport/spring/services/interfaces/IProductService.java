package com.globalsoftwaresupport.spring.services.interfaces;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> findAllProducts();

    void saveProduct(ProductRequest productRequest);

    ProductResponse findProductById(Long id);

    void updateProduct(ProductRequest productRequest);
}
