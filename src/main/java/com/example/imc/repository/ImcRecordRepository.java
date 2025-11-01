package com.example.imc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.imc.model.ImcRecord;
import com.example.imc.model.User;
import java.util.List;

public interface ImcRecordRepository extends JpaRepository<ImcRecord, Long> {
    List<ImcRecord> findByUserOrderByRecordedAtDesc(User user);
}