package com.project.ExamiNation.model.dto;

import java.util.List;

public class ExamSubmissionDto {
    private Long examSessionId;
    private List<AnswerDto> answers;

    // Getters and Setters
    public Long getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(Long examSessionId) {
        this.examSessionId = examSessionId;
    }
    
    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public static class AnswerDto {
        private Long questionId;
        private Integer selectedAnswer;

        // Getters and Setters
        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public Integer getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(Integer selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }
    }
}
