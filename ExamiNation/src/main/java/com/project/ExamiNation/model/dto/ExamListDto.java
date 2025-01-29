package com.project.ExamiNation.model.dto;

import java.util.Date;

public class ExamListDto {
    private Long id;
    private String title;
    private String status;
    private int totalQuestions;
    private double weight;
    private Date createdAt;
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    public int getTotalQuestions() {
      return totalQuestions;
    }
    public void setTotalQuestions(int totalQuestions) {
      this.totalQuestions = totalQuestions;
    }
    public double getWeight() {
      return weight;
    }
    public void setWeight(double weight) {
      this.weight = weight;
    }
    public Date getCreatedAt() {
      return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
    }

}
