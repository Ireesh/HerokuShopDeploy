package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findProductByName(String name);
    Product findProductById(Long id);
}
