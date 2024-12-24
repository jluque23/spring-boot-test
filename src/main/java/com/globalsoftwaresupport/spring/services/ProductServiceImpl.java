package com.globalsoftwaresupport.spring.services;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductsResponse;
import com.globalsoftwaresupport.spring.repositories.dao.ProductRepository;
import com.globalsoftwaresupport.spring.repositories.entity.Product;
import com.globalsoftwaresupport.spring.services.interfaces.IProductService;
import com.globalsoftwaresupport.spring.utils.ProductGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductGenerator productGenerator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productGenerator = new ProductGenerator();
    }

    @Override
    public List<ProductsResponse> findAllProducts() {

        List<Product> productsList = productRepository.findAll();

        return productsList.stream()
                .map(product -> new ProductsResponse(product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getCategory(),
                        product.getCreatedAt(),
                        product.getUpdatedAt(),
                        product.isActive())).toList();

    }


    @Override
    public void saveProduct(ProductRequest productRequest) {
       Product productToRegister = productGenerator.generateProduct(productRequest);

       productRepository.save(productToRegister);

    }

    @Override
    public ProductsResponse findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return productGenerator.generateProductsResponse(product);
        }

        return new ProductsResponse();
    }
}
