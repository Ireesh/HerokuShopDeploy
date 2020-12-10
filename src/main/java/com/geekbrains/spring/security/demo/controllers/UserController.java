package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/auth/profile")
    public String showUserData(Model model, Principal principal) {
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

}
