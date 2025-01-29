package com.project.ExamiNation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ExamiNation.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExam_Id(Long examId); // Find questions by exam ID
}
