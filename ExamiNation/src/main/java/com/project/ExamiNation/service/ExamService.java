package com.project.ExamiNation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ExamiNation.model.Exam;
import com.project.ExamiNation.repository.ExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    // Create or Update
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    // Read
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    public List<Exam> getExamsByTeacherId(Long teacherId) {
        return examRepository.findByCreatedBy_Id(teacherId);
    }

    public List<Exam> getExamsByStatus(String status) {
        return examRepository.findByStatus(status);
    }

    // Delete
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}
