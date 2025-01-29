package com.project.ExamiNation.repository;

import com.project.ExamiNation.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCreatedBy_Id(Long userId); // Find exams by teacher (user) ID
    List<Exam> findByStatus(String status);
}
