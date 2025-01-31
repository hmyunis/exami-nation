package com.project.ExamiNation.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.ExamiNation.model.Exam;
import com.project.ExamiNation.model.User;

public class DashboardExamSessionDto {
  private Long id;
  private Exam exam;
  private List<User> participants;
  private int duration;
  private String status;
  private LocalTime startTime;

  private int totalQuestions;
  private LocalDate examDate;
  private LocalTime endTime;

  // nullable
  private boolean isParticipant;

  public boolean isParticipant() {
    return isParticipant;
  }

  public void setIsParticipant(boolean isParticipant) {
    this.isParticipant = isParticipant;
  }

  public DashboardExamSessionDto(Long id, Exam exam, List<User> participants, int duration, String status, Date startTime) {
    this.id = id;
    this.exam = exam;
    if(participants == null){
      this.participants = new ArrayList<User>();
    } else {
      this.participants = participants;
    }
    this.duration = duration;
    this.status = status;
    this.startTime = startTime.toInstant()
                              .atZone(java.time.ZoneId.systemDefault())
                              .toLocalTime();
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Exam getExam() {
    return exam;
  }
  public void setExam(Exam exam) {
    this.exam = exam;
  }
  public List<User> getParticipants() {
    return participants;
  }
  public void setParticipants(List<User> participants) {
    this.participants = participants;
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
  public LocalTime getStartTime() {
    return startTime;
  }
  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }
  public int getTotalQuestions() {
    return totalQuestions;
  }
  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }
  public LocalDate getExamDate() {
    return examDate;
  }
  public void setExamDate(LocalDate examDate) {
    this.examDate = examDate;
  }
  public LocalTime getEndTime() {
    return endTime;
  }
  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  
}
