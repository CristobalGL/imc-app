package com.example.imc.service;

import org.springframework.stereotype.Service;
import com.example.imc.model.ImcRecord;
import com.example.imc.model.User;
import com.example.imc.repository.ImcRecordRepository;
import java.util.List;

@Service
public class ImcService {
    private final ImcRecordRepository repo;
    public ImcService(ImcRecordRepository repo){ this.repo = repo; }

    public double calcularImc(double pesoKg, double alturaM) {
        // assume alturaM validated earlier (1m..2.5m)
        return pesoKg / (alturaM * alturaM);
    }

    public ImcRecord saveImcRecord(User user, double pesoKg) {
        double imc = calcularImc(pesoKg, user.getHeight());
        ImcRecord record = new ImcRecord(user, pesoKg, imc);
        return repo.save(record);
    }

    public List<ImcRecord> getHistory(User user) {
        return repo.findByUserOrderByRecordedAtDesc(user);
    }
}