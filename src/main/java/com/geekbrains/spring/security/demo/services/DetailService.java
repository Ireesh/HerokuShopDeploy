package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.Detail;
import com.geekbrains.spring.security.demo.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public List<Detail> showAllDetailsOfOrder(Long id) {
        return detailRepository.findDetailsByOrderId(id);
    }

    public void createNewDetailOfOrder(Detail detail) { detailRepository.save(detail); }
}
