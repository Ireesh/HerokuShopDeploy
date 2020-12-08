package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String showAllProducts(Model model, @RequestParam Map<String, String> requestParam) {
        Integer pageNumber = Integer.parseInt(requestParam.getOrDefault("p", "1"));
        Page<Product> products = productService.findAll(pageNumber);
        model.addAttribute("products", products);
        return "/products";
    }

    @GetMapping("/products/edit")
    public String editProductForm() {
        return "product_edit";
    }
}
