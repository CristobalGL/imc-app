package com.example.imc.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name", length = 100)
    private String fullName;

    @Column(name="username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 1)
    private String sex; // M/F

    @Column(name="height_m", nullable = false)
    private Double height; // meters

    @Column(name="password", nullable = false)
    private String password; // NOTE: almacenar en claro sólo para demo (usar hash en producción)

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ImcRecord> records;

    // Getters / setters
    // ... constructor vacío
    public User() {}
    // getters and setters omitted for brevity - implement all
}