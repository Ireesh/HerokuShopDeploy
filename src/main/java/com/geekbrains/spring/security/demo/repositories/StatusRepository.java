package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findStatusById(Long id);
}
