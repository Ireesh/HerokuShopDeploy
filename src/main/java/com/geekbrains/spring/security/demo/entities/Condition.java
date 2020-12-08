package com.geekbrains.spring.security.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "conditions")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


}
