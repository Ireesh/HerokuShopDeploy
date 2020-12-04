package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.StatusService;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user );
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("statuses", statusService.findAll());
        return "admin_edit";
    }

    @RequestMapping(value="admin/edit", method = RequestMethod.POST)
    public String editUser (@Validated String idStatus, Long idUser) {
        System.out.println(idStatus);
        System.out.println(idUser);
        User user = new User();
        user = userService.findUserById(idUser);
        user.setStatus(statusService.findStatusById(Long.valueOf(idStatus)));
        userService.updateUserStatus(user);
        return "redirect:/admin";
    }


}
