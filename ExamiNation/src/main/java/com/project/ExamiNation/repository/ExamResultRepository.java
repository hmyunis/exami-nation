package com.project.ExamiNation.repository;

import com.project.ExamiNation.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    // Custom query to find results by exam session ID
    List<ExamResult> findByExamSessionId(Long examSessionId);

    // Custom query to find results by student ID
    List<ExamResult> findByStudentId(Long studentId);
}