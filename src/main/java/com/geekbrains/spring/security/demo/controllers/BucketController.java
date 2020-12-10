package com.geekbrains.spring.security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BucketController {
    @GetMapping("/auth/profile/bucket")
    public String bucketPage() {
        return "bucket";
    }
}
