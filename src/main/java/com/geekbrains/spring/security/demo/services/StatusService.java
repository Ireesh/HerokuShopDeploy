package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.repositories.StatusRepository;
import com.geekbrains.spring.security.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private StatusRepository statusRepository;

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status findStatusById(Long id) {
        return statusRepository.findStatusById(id);
    }
}
