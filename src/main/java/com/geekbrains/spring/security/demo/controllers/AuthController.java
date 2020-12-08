package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/auth")
    public String authenticatedPage() {
        return "redirect:/auth/profile";
    }

}
