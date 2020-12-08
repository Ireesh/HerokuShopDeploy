package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productRepository.findAll(PageRequest.of(page - 1, 2));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
