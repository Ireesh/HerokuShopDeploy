package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Order;
import com.geekbrains.spring.security.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
}
