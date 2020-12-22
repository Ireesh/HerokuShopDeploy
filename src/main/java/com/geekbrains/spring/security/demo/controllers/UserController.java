package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.dto.ProductDto;
import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BucketService bucketService;

    @LogMethod
    @GetMapping("/auth/profile")
    public String showUserData(Model model, Principal principal, HttpServletRequest request) {
        User user = userService.findUserByEmail(principal.getName());
        if (user.getRole().equals(Role.ADMIN)) {
            return "redirect:/auth/admin";
        }
        if (user.getRole().equals(Role.MANAGER)) {
            return "redirect:/products";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/auth/profile")
    public String authenticatedPage() {
        return "redirect:/auth/profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String singUpNewUser(@Validated String username, String email, String password,
                                String confirmPassword, Long phone) {
        if (userService.createNewUser(username, email, password, confirmPassword, phone)) {
            return "redirect:/login";
        } else {
            return "redirect:/login?regError";
        }
    }

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
        }
    }

    @MessageMapping("/auth/profile")
    public void messageAddProductToBucket(Model model, Principal principal) {
        modelAttribute(model, principal);
    }
}
