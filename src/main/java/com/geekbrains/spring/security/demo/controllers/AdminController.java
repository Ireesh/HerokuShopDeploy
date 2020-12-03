package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    List<User> users = new ArrayList<>();

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin_edit";
    }

    @PostMapping("/admin/edit")
    public String modifyProduct(@ModelAttribute User user) {
        userService.updateUserStatus(user);
        return "redirect:/admin";
    }
}
