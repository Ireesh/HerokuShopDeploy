package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.UserService;
import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class BucketController {

    @Autowired
    BucketService bucketService;
    @Autowired
    UserService userService;
    @Autowired
    UserSessionHandler userSessionHandler;

    @GetMapping("/auth/profile/bucket")
    public String aboutBucket(Model model, Principal principal, HttpServletRequest request){
        BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
        model.addAttribute("bucket", bucketDto);
        userSessionHandler.makeSign(principal, request);
        return "bucket";
    }

}
