package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
