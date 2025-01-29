package com.project.ExamiNation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ExamiNation.model.Exam;
import com.project.ExamiNation.service.ExamService;

@Controller
public class HomeController {

  @Autowired
  private ExamService examService;
  
  @GetMapping({"", "/"})
  public String index() {
    return "index";
  }

  @GetMapping("/dashboard")
  public String home(Model model) {
    List<Exam> publishedExams = examService.getExamsByStatus("PUBLISHED");
    List<Exam> unpublishedExams = examService.getExamsByStatus("DRAFT");
    model.addAttribute("liveExams", publishedExams);
    model.addAttribute("draftExams", unpublishedExams);
    return "teacher/dashboard";
  }
}
