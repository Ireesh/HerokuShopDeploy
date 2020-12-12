package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
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


    @GetMapping("/")
    public String homePage(HttpServletRequest request, Principal principal) {
        userSessionHandler.makeSign(principal, request);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Principal principal) {
        userSessionHandler.makeSign(principal, request);
        return "login_page";
    }

    @GetMapping("/auth")
    public String authenticatedPage() {
        return "redirect:/auth/profile";
    }

}
