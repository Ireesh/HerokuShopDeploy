package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import com.geekbrains.spring.security.demo.mappers.ProductMapper;
import com.geekbrains.spring.security.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    BucketService bucketService;
    @Autowired
    UserService userService;
    private final ProductMapper mapper = ProductMapper.MAPPER;

    @LogMethod
    @GetMapping("/products")
    public String showAllProducts(Model model, @RequestParam Map<String,
            String> requestParam, HttpServletRequest request, Principal principal) {
        Integer pageNumber = Integer.parseInt(requestParam.getOrDefault("p", "1"));
        Page<Product> products = productService.findAll(pageNumber);
        model.addAttribute("products", products);
        return "/products";
    }

    @GetMapping("/products/{id}/bucket")
    public String addProductToBucket(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/products";
        }

        bucketService.addProductToBucket(mapper.fromProduct(productService.findProductById(id)), principal.getName());
        return "redirect:/products";
    }

    @LogMethod
    @GetMapping("/products/edit")
    public String editProductForm(HttpServletRequest request, Principal principal) {
        return "admin_edit_products";
    }

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
        }
    }
}
