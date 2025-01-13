package com.globalsoftwaresupport.spring.services;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductResponse;
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
    public List<ProductResponse> findAllProducts() {

        List<Product> productsList = productRepository.findAll();

        return productsList.stream()
                .map(product -> new ProductResponse(product.getId(),
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
    public ProductResponse findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return productGenerator.generateProductsResponse(product);
        }

        return new ProductResponse();
    }

    @Override
    public void updateProduct(ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(productRequest.getId());

        if(productOptional.isPresent()){
            Product product = productOptional.get();

            product.setId(productOptional.get().getId());
            productRequestToProduct(productRequest, product);

            productRepository.save(product);
        }
    }

    public static void productRequestToProduct(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setCreatedAt(productRequest.getCreatedAt());
        product.setUpdatedAt(productRequest.getUpdatedAt());
        product.setActive(productRequest.isActive());
    }
}
