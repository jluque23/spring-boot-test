package com.globalsoftwaresupport.spring.controllers;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductsResponse;
import com.globalsoftwaresupport.spring.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    //GET /products - List all products.
    //GET /products/{id} - Get product details.
    //POST /products - Add a new product.
    private final IProductService productService;

    @Autowired
    public ProductController(final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductsResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductsResponse> getProductById(@PathVariable long id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerProduct(@RequestBody ProductRequest productRequest) {

        productService.saveProduct(productRequest);

        return ResponseEntity.ok("Product registered successfully Product: " + productRequest.getName());
    }
}
