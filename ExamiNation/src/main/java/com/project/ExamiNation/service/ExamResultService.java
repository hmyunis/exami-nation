package com.project.ExamiNation.service;

import com.project.ExamiNation.model.ExamResult;
import com.project.ExamiNation.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    // Save an exam result
    public ExamResult saveExamResult(ExamResult examResult) {
        return examResultRepository.save(examResult);
    }

    // Get all results for a specific exam session
    public List<ExamResult> getResultsByExamSession(Long examSessionId) {
        return examResultRepository.findByExamSessionId(examSessionId);
    }

    // Get all results for a specific student
    public List<ExamResult> getResultsByStudent(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    // Delete an exam result
    public void deleteExamResult(Long id) {
        examResultRepository.deleteById(id);
    }
}