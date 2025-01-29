package com.project.ExamiNation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ExamiNation.model.Question;
import com.project.ExamiNation.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    // Create or Update
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Read
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExam_Id(examId);
    }

    // Delete
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
