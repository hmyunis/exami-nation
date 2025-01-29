package com.project.ExamiNation.model.dto;

import java.util.Date;
import java.util.List;

import com.project.ExamiNation.model.Question;

public class ExamDetailDto {
  private Long id;
  private String title;
  private String status;
  private int totalQuestions;
  private double weight;
  private Date createdAt;

  List<Question> questions;

  

 public ExamDetailDto(Long id, String title, String status, int totalQuestions, double weight, Date createdAt,
      List<Question> questions) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.totalQuestions = totalQuestions;
    this.weight = weight;
    this.createdAt = createdAt;
    this.questions = questions;
  }

public int getTotalQuestions() {
  return totalQuestions;
}

public void setTotalQuestions(int totalQuestions) {
  this.totalQuestions = totalQuestions;
}

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

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

}
