package com.project.ExamiNation.model.dto;

import java.util.Date;

public class ResultHistoryDto {
  private Long id;
  private String examTitle;
  private Date startTime;
  private int duration;
  private String status;
  private double yourScore;
  private int totalQuestions;
  private double weight;
  
  private int correctAnswers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExamTitle() {
    return examTitle;
  }

  public void setExamTitle(String examTitle) {
    this.examTitle = examTitle;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getYourScore() {
    return yourScore;
  }

  public void setYourScore(double yourScore) {
    this.yourScore = yourScore;
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

  public int getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers(int correctAnswers) {
    this.correctAnswers = correctAnswers;
  }


  
}
