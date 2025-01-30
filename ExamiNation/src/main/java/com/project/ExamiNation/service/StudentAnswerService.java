package com.project.ExamiNation.service;

import com.project.ExamiNation.model.StudentAnswer;
import com.project.ExamiNation.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    // Save a student's answer
    public StudentAnswer saveStudentAnswer(StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }

    // Get all answers for a specific exam session and student
    public List<StudentAnswer> getAnswersByExamSessionAndStudent(Long examSessionId, Long studentId) {
        return studentAnswerRepository.findByExamSessionIdAndStudentId(examSessionId, studentId);
    }

    // Get all answers for a specific exam session
    public List<StudentAnswer> getAnswersByExamSession(Long examSessionId) {
        return studentAnswerRepository.findByExamSessionId(examSessionId);
    }

    // Delete a student's answer
    public void deleteStudentAnswer(Long id) {
        studentAnswerRepository.deleteById(id);
    }
}