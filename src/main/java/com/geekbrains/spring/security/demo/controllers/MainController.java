package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.UserService;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserSessionHandler userSessionHandler;
    @Autowired
    BucketService bucketService;
    @Autowired
    UserService userService;

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

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
        }
    }

}
