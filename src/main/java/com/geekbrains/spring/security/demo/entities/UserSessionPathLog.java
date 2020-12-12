package com.geekbrains.spring.security.demo.entities;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_session_path_log")
public class UserSessionPathLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "principal_name")
    private String  name;

    @Column(name = "date")
    private String date;

    @Column(name = "path")
    private String path;
}
