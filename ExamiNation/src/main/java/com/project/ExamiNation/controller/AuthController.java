package com.project.ExamiNation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.ExamiNation.model.User;
import com.project.ExamiNation.model.dto.RegisterDto;
import com.project.ExamiNation.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class AuthController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String loginPage() {
      return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    RegisterDto registerDto = new RegisterDto();
    model.addAttribute(registerDto);
    model.addAttribute("success", false);
    return "register";
  }

  @PostMapping("/register")
  public String register(
    Model model,
    @Valid @ModelAttribute RegisterDto registerDto,
    BindingResult result
  ) {

    if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
      result.addError(new FieldError("registerDto", "confirmPassword", "Password and confirm password do not match"));
    }

    Optional<User> existingUser = userRepository.findByUsername(registerDto.getUsername());
    if (existingUser.isPresent()) {
        result.addError(new FieldError("registerDto", "username", "Username already exists"));
    }

    // also make sure the email is not taken
    existingUser = userRepository.findByEmail(registerDto.getEmail());
    if (existingUser.isPresent()) {
        result.addError(new FieldError("registerDto", "email", "Email already exists"));
    }

    if(result.hasErrors()){
      return "register";
    }

    try {
      var bCryptEncoder = new BCryptPasswordEncoder();
      User newUser = new User();
      newUser.setFullName(registerDto.getFullName());
      newUser.setEmail(registerDto.getEmail());
      newUser.setUsername(registerDto.getUsername());
      newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
      newUser.setRole("STUDENT");
      userRepository.save(newUser);

      model.addAttribute("registerDto", new RegisterDto());
      model.addAttribute("success", true);
    } catch (Exception e) {
      result.addError(new FieldError("registerDto", "fullName", e.getMessage()));
    }

    
    return "register";
  }
}
