package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}