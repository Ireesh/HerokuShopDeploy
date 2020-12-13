package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import com.geekbrains.spring.security.demo.services.ProductService;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class ProductController {
    private UserSessionPathLog userSessionPathLog;
    @Autowired
    ProductService productService;
    @Autowired
    UserSessionHandler userSessionHandler;

    @LogMethod
    @GetMapping("/products")
    public String showAllProducts(Model model, @RequestParam Map<String,
            String> requestParam, HttpServletRequest request, Principal principal) {
        Integer pageNumber = Integer.parseInt(requestParam.getOrDefault("p", "1"));
        Page<Product> products = productService.findAll(pageNumber);
        model.addAttribute("products", products);
        return "/products";
    }

    @LogMethod
    @GetMapping("/products/edit")
    public String editProductForm(HttpServletRequest request, Principal principal) {
        return "admin_edit_products";
    }
}
