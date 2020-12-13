package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.UserService;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    UserSessionHandler userSessionHandler;
    @Autowired
    BucketService bucketService;

    @LogMethod
    @GetMapping("/auth/admin/users")
    public String adminUsersControlPage(Model model, Principal principal, @RequestParam Map<String,
            String> requestParams, HttpServletRequest request) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        Page<User> users = userService.findAll(pageNumber);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("user", user );
        return "users";
    }

    @LogMethod
    @GetMapping("/auth/admin")
    public String adminPage(HttpServletRequest request, Principal principal) {
        return "admin";
    }

    @GetMapping("/auth/admin/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request,
                               Principal principal) {
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

    @LogMethod
    @GetMapping("/auth/admin/history")
    public String historyPage(Model model, HttpServletRequest request, Principal principal) {
        model.addAttribute("history", userSessionHandler.showAllHistory());
        return "history_user_path";
    }

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
        }
    }
}
