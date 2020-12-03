package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/authenticated/profile")
    public String showUserData(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        for (Role r : user.getRoles()) {
            if (r.getName().equals("admin")) {
                return "redirect:/admin";
            }
        }
        model.addAttribute("user", user);
        return "profile";
    }

}
