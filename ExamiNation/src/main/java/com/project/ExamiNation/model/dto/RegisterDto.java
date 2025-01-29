package com.project.ExamiNation.model.dto;

import jakarta.validation.constraints.*;

public class RegisterDto {

  @NotEmpty
  private String fullName;
  
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  @NotEmpty
  @Email
  private String email;
  
  @NotEmpty
  private String username;
  
  @NotEmpty
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;
  
  private String confirmPassword;
}
