package com.globalsoftwaresupport.spring.repositories.dao;

import com.globalsoftwaresupport.spring.repositories.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
