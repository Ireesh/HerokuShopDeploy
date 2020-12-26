package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.configs.OrderIntegrationConfig;
import com.geekbrains.spring.security.demo.dto.BucketDetailDto;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.dto.OrderIntegrationDto;
import com.geekbrains.spring.security.demo.entities.Condition;
import com.geekbrains.spring.security.demo.entities.Detail;
import com.geekbrains.spring.security.demo.entities.Order;
import com.geekbrains.spring.security.demo.repositories.DetailRepository;
import com.geekbrains.spring.security.demo.repositories.OrderRepository;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private BucketService bucketService;
    private OrderIntegrationConfig integrationConfig;

    public OrderService(OrderIntegrationConfig orderIntegrationConfig) {
        this.integrationConfig = orderIntegrationConfig;
    }

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
            sendIntegrationNotify(order);
            bucketService.cleanBucket(order.getUser());
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

    private void sendIntegrationNotify(Order order) {
        Long orderId = order.getId();
        OrderIntegrationDto dto = new OrderIntegrationDto();
        dto.setUserEmail(order.getUser().getEmail());
        dto.setOrderId(orderId);
        List<OrderIntegrationDto.OrderDetailsDto> details = detailService.showAllDetailsOfOrder(orderId).stream()
                .map(OrderIntegrationDto.OrderDetailsDto::new).collect(Collectors.toList());
        dto.setDetails(details);

        Message<OrderIntegrationDto> message = MessageBuilder.withPayload(dto)
                .setHeader("Content-type", "application/json")
                .build();

        integrationConfig.getOrdersChannel().send(message);
    }

}
