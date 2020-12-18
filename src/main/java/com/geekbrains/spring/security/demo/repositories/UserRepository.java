package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    User findUserById(Long id);
    User findUserByEmail(String email);
}
