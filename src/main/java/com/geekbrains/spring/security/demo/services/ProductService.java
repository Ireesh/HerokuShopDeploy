package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.dto.ProductDto;
import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.mappers.ProductMapper;
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
    private final ProductMapper mapper = ProductMapper.MAPPER;

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

    public List<ProductDto> findAll() {
        return mapper.fromProductsList(productRepository.findAll());
    }
}
