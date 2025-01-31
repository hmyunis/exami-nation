package com.project.ExamiNation.model.dto;

import java.util.Date;

public class SessionHistoryDto {
  private Long id;
  private String examTitle;
  private Date startTime;
  private int duration;
  private String status;
  private int participants;
  private double weight;
  private int totalQuestions;
  
  private double averageScore;
  private double highestScore;
  private double lowestScore;
  
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
  public int getParticipants() {
    return participants;
  }
  public void setParticipants(int participants) {
    this.participants = participants;
  }
  public double getWeight() {
    return weight;
  }
  public void setWeight(double weight) {
    this.weight = weight;
  }
  public int getTotalQuestions() {
    return totalQuestions;
  }
  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }
  public double getAverageScore() {
    return averageScore;
  }
  public void setAverageScore(double averageScore) {
    this.averageScore = averageScore;
  }
  public double getHighestScore() {
    return highestScore;
  }
  public void setHighestScore(double highestScore) {
    this.highestScore = highestScore;
  }
  public double getLowestScore() {
    return lowestScore;
  }
  public void setLowestScore(double lowestScore) {
    this.lowestScore = lowestScore;
  }
  
    
}
