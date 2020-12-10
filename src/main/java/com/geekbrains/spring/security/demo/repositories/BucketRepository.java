package com.geekbrains.spring.security.demo.repositories;

import com.geekbrains.spring.security.demo.entities.Bucket;
import com.geekbrains.spring.security.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Bucket findBucketByUser(User user);
}
