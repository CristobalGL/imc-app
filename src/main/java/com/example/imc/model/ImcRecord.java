package com.example.imc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imc_records")
public class ImcRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable=false)
    private Double weightKg;

    @Column(nullable=false)
    private Double imc;

    @Column(name="recorded_at", nullable=false)
    private LocalDateTime recordedAt;

    public ImcRecord() {}

    public ImcRecord(User user, Double weightKg, Double imc) {
        this.user = user;
        this.weightKg = weightKg;
        this.imc = imc;
        this.recordedAt = LocalDateTime.now();
    }
    // getters & setters
}