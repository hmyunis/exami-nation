package com.project.ExamiNation.repository;

import com.project.ExamiNation.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    // Custom query to find all answers by a specific student in an exam session
    List<StudentAnswer> findByExamSessionIdAndStudentId(Long examSessionId, Long studentId);

    // Custom query to find all answers for a specific exam session
    List<StudentAnswer> findByExamSessionId(Long examSessionId);
}