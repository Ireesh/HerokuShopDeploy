package com.geekbrains.spring.security.demo.controllers;

import com.geekbrains.spring.security.demo.aspects.LogMethod;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.entities.Detail;
import com.geekbrains.spring.security.demo.entities.Order;
import com.geekbrains.spring.security.demo.services.BucketService;
import com.geekbrains.spring.security.demo.services.DetailService;
import com.geekbrains.spring.security.demo.services.OrderService;
import com.geekbrains.spring.security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private UserService userService;
    @Autowired
    private DetailService detailService;

    @LogMethod
    @GetMapping("/auth/orders")
    public String aboutBucket(Model model, Principal principal, HttpServletRequest request){
        List<Detail> details = new ArrayList<>();
        List<Order> orders = orderService.findAllOrdersByUser(principal.getName());
        for (Order o : orders) {
            details.addAll(detailService.showAllDetailsOfOrder(o.getId()));
        }
        model.addAttribute("orders", orders);
        model.addAttribute("details", details);
        return "order";
    }

    @PostMapping("/auth/orders")
    public String saveNewOrder(Principal principal) {
        BucketDto bucketDto = (bucketService.findBucketByUser(userService.findUserByEmail(principal.getName())));
        orderService.createNewOrder(bucketDto, principal.getName());
        return "redirect:/auth/orders";
    }

    @ModelAttribute
    public void modelAttribute(Model model, Principal principal) {
        if (principal != null) {
            BucketDto bucketDto = bucketService.findBucketByUser(userService.findUserByEmail(principal.getName()));
            model.addAttribute("amount", bucketDto.getAmountProducts());
            model.addAttribute("totalPrice", bucketDto.getSum());
        }
    }
}
