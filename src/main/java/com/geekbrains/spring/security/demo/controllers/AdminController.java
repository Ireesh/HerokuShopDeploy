package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/auth/admin/users")
    public String adminUsersControlPage(Model model, Principal principal, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        Page<User> users = userService.findAll(pageNumber);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("user", user );
        return "users";
    }

    @GetMapping("/auth/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/auth/admin/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("statuses", Status.values());
        return "admin_edit_users";
    }

    @RequestMapping(value="/auth/admin/users/edit", method = RequestMethod.POST)
    public String editUser (@Validated String status, Long idUser) {
        User user = new User();
        user = userService.findUserById(idUser);
        user.setStatus(Status.valueOf(status));
        userService.updateUserStatus(user);
        return "redirect:/auth/admin/users";
    }


}
