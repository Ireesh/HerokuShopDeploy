package com.geekbrains.spring.security.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private int amount;

    @Column (name = "price")
    private BigDecimal price;

    @OneToOne(mappedBy = "details")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "details_products",
            joinColumns = @JoinColumn(name = "detail_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Product product;
}
