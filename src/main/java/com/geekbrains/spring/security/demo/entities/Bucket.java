package com.geekbrains.spring.security.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "buckets_products",
                joinColumns = @JoinColumn(name = "bucket_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
