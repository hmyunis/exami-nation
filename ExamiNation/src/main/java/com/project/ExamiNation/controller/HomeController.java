package com.project.ExamiNation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ExamiNation.model.User;
import com.project.ExamiNation.service.UserService;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @GetMapping({"", "/"})
  public String index() {
    return "index";
  }

  @GetMapping("/dashboard")
  public String home() {
    User user = userService.getCurrentUser();
    if (user.getRole().equals("TEACHER")) {
      return "redirect:/teacher/dashboard";
    } else if (user.getRole().equals("STUDENT")) {
      return "redirect:/student/dashboard";
    }
    return "redirect:/";
  }
}
