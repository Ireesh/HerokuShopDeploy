package com.geekbrains.spring.security.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private String name;
    private BigDecimal price;
    private double count;
}
