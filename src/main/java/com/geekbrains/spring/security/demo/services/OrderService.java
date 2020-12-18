package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.dto.BucketDetailDto;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.entities.Condition;
import com.geekbrains.spring.security.demo.entities.Detail;
import com.geekbrains.spring.security.demo.entities.Order;
import com.geekbrains.spring.security.demo.repositories.DetailRepository;
import com.geekbrains.spring.security.demo.repositories.OrderRepository;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private DetailService detailService;

    public List<Order> findAllOrdersByUser(String email) {
        return orderRepository.findOrdersByUser(userService.findUserByEmail(email));
    }

    @Transactional
    public void createNewOrder(BucketDto bucketDto, String email) {
        if (bucketDto != null) {
            Order order = new Order();
            Date date = java.util.Calendar.getInstance().getTime();
            order.setCondition(Condition.CREATED);
            order.setTotalPrice(bucketDto.getSum());
            order.setUser(userService.findUserByEmail(email));
            orderRepository.save(order);
            createNewDetails(bucketDto, order);
            System.out.println(order.getCreated().toString());
        }
    }

    @Transactional
    public void createNewDetails(BucketDto bucketDto, Order order) {
        for (BucketDetailDto details : bucketDto.getBucketDetails()) {
            Detail detail = new Detail();
            detail.setOrder(order);
            detail.setProduct(productService.findProductById(details.getProductId()));
            detail.setPrice(details.getPrice());
            detail.setAmount(details.getAmount());
            detailService.createNewDetailOfOrder(detail);
        }
    }

}
