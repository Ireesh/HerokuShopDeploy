package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserSessionHandler userSessionHandler;

    @LogMethod
    @GetMapping("/")
    public String homePage(HttpServletRequest request, Principal principal) {
        return "index";
    }

    @LogMethod
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Principal principal) {
        return "login_page";
    }

    @LogMethod
    @GetMapping("/auth")
    public String authenticatedPage() {
        return "redirect:/auth/profile";
    }

}
