package com.project.ExamiNation.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class DashboardExamDto {
  private Long id;
  private String title;
  private String description;
  private LocalDate examDate;
  private int totalQuestions;
  private int durationInMin;
  private LocalTime startTime;
  private LocalTime endTime;
  private double weight;
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
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public LocalDate getExamDate() {
    return examDate;
  }
  public void setExamDate(LocalDate examDate) {
    this.examDate = examDate;
  }
  public int getTotalQuestions() {
    return totalQuestions;
  }
  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }
  public int getDurationInMin() {
    return durationInMin;
  }
  public void setDurationInMin(int durationInMin) {
    this.durationInMin = durationInMin;
  }
  public LocalTime getStartTime() {
    return startTime;
  }
  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }
  public LocalTime getEndTime() {
    return endTime;
  }
  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }
  public double getWeight() {
    return weight;
  }
  public void setWeight(double weight) {
    this.weight = weight;
  }
}
