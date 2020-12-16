package com.geekbrains.spring.security.demo.mappers;

import com.geekbrains.spring.security.demo.dto.ProductDto;
import com.geekbrains.spring.security.demo.entities.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct (ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto fromProduct (Product product);

    List<Product> toProductsList (List<ProductDto> productsDto);

    @InheritInverseConfiguration
    List<ProductDto> fromProductsList (List<Product> products);
}
