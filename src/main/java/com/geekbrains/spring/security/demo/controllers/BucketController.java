package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.dto.ProductDto;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.UserService;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class BucketController {

    @Autowired
    BucketService bucketService;
    @Autowired
    UserService userService;
    @Autowired
    UserSessionHandler userSessionHandler;

    @LogMethod
    @GetMapping("/auth/profile/bucket")
    public String aboutBucket(Model model, Principal principal, HttpServletRequest request){
        BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
        model.addAttribute("bucket", bucketDto);
        return "bucket";
    }

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
        }
    }

    @PostMapping("/auth/profile/bucket")
    public ResponseEntity<Void> addProductToBucket(ProductDto productDto, Principal principal) {
        bucketService.addProductToBucket(productDto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/auth/profile/bucket")
    public void messageAddProductToBucket(ProductDto productDto, Principal principal) {
        bucketService.addProductToBucket(productDto, principal.getName());
    }
}
