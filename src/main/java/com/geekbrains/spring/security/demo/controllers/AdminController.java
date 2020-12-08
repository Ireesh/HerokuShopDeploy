package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.StatusService;
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

    @Autowired
    StatusService statusService;

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        Page<User> users = userService.findAll(pageNumber);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("user", user );
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("statuses", statusService.findAll());
        return "admin_edit";
    }

    @RequestMapping(value="/admin/edit", method = RequestMethod.POST)
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
