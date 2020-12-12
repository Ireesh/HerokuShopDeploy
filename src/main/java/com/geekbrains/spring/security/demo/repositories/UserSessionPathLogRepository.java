package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionPathLogRepository extends JpaRepository<UserSessionPathLog, Long> {
}
