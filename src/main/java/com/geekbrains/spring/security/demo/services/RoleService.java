package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.repositories.RoleRepository;
import com.geekbrains.spring.security.demo.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }
}
