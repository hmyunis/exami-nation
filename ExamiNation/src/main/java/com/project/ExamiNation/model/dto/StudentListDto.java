package com.project.ExamiNation.model.dto;

import java.util.Date;

public class StudentListDto {
  private Long id;
  private String fullName;
  private String username;
  private String email;
  private String role;
  private Date dateJoined;
  private double totalMarks;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public Date getDateJoined() {
    return dateJoined;
  }
  public void setDateJoined(Date dateJoined) {
    this.dateJoined = dateJoined;
  }
  public double getTotalMarks() {
    return totalMarks;
  }
  public void setTotalMarks(double totalMarks) {
    this.totalMarks = totalMarks;
  }
}
