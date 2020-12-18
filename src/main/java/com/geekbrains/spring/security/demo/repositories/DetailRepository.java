package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findDetailsByOrderId(Long id);
}
