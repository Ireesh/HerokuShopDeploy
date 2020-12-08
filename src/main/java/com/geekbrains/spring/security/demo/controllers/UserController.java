package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.services.RoleService;
import com.geekbrains.spring.security.demo.services.StatusService;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Arrays;

//what to need:
//safe phone by pattern
//send error on form registration
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    StatusService statusService;
    @Autowired
    RoleService roleService;

    @GetMapping("/auth/profile")
    public String showUserData(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        for (Role r : user.getRoles()) {
            if (r.getName().equals("admin")) {
                return "redirect:/admin";
            }
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String singUpNewUser(Model model, @Validated String username, String email, String password,
                                String confirmPassword, Long phone) {
        if (username != null && email != null && password != null && confirmPassword != null && phone != null) {
            if (!password.equals(confirmPassword)) {
                System.out.println("Password doesn't match");
                return "redirect:/login";
            }
            if (userService.findUserByEmail(email) == null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(bCryptPasswordEncoder.encode(password));
                user.setEmail(email);
                user.setPhone(phone.toString());
                user.setStatus(statusService.findStatusById(1L));
                user.setRoles(Arrays.asList(roleService.findRoleById(2L)));
                userService.createNewUser(user);
                return "redirect:/login";
            } else {
                System.out.println("Already exist");
                return "redirect:/login";
            }
        } else {
            System.out.println("Fill in all fields");
            return "redirect:/login";
        }
    }
}
