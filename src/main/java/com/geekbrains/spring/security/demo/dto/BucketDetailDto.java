package com.geekbrains.spring.security.demo.dto;

import com.geekbrains.spring.security.demo.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDto {
    private String name;
    private Long productId;
    private BigDecimal price;
    private int amount;
    private BigDecimal sum;

    public BucketDetailDto(Product product){
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.amount = 1;
        this.sum = product.getPrice();
    }
}
