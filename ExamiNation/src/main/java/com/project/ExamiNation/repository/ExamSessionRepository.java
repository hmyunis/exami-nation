package com.project.ExamiNation.repository;

import com.project.ExamiNation.model.ExamSession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {
    // Custom query to find exam sessions by status (e.g., "OPEN" or "CLOSED")
    List<ExamSession> findByStatus(String status);

    // Custom query to find exam sessions by exam ID
    List<ExamSession> findByExamId(Long examId);
}