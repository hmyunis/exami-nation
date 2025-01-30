package com.project.ExamiNation.service;

import com.project.ExamiNation.model.ExamSession;
import com.project.ExamiNation.repository.ExamSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionService {

    @Autowired
    private ExamSessionRepository examSessionRepository;

    // Create a new exam session
    public ExamSession createExamSession(ExamSession examSession) {
        return examSessionRepository.save(examSession);
    }

    // Get all exam sessions
    public List<ExamSession> getAllExamSessions() {
        return examSessionRepository.findAll();
    }

    // Get exam session by ID
    public ExamSession getExamSessionById(Long id) {
        return examSessionRepository.findById(id).orElse(null);
    }

    // Update an exam session
    public ExamSession updateExamSession(Long id, ExamSession updatedExamSession) {
        ExamSession existingExamSession = examSessionRepository.findById(id).orElse(null);
        if (existingExamSession != null) {
            existingExamSession.setExam(updatedExamSession.getExam());
            existingExamSession.setParticipants(updatedExamSession.getParticipants());
            existingExamSession.setDuration(updatedExamSession.getDuration());
            existingExamSession.setStatus(updatedExamSession.getStatus());
            existingExamSession.setStartTime(updatedExamSession.getStartTime());
            return examSessionRepository.save(existingExamSession);
        }
        return null;
    }

    // update status by id
    public void updateStatusById(Long id, String status) {
        ExamSession examSession = getExamSessionById(id);
        if (examSession != null) {
            examSession.setStatus(status);
            examSessionRepository.save(examSession);
        }
    }

    // Delete an exam session
    public void deleteExamSession(Long id) {
        examSessionRepository.deleteById(id);
    }

    // Find exam sessions by status
    public List<ExamSession> getExamSessionsByStatus(String status) {
        return examSessionRepository.findByStatus(status);
    }

    // Find exam sessions by exam ID
    public List<ExamSession> getExamSessionsByExamId(Long examId) {
        return examSessionRepository.findByExamId(examId);
    }
}