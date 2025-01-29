package com.project.ExamiNation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ExamiNation.model.Exam;
import com.project.ExamiNation.model.Question;
import com.project.ExamiNation.model.User;
import com.project.ExamiNation.service.ExamService;
import com.project.ExamiNation.service.QuestionService;
import com.project.ExamiNation.service.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

  @Autowired
  private ExamService examService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private UserService userService;
  
  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    List<Exam> publishedExams = examService.getExamsByStatus("PUBLISHED");
    List<Exam> unpublishedExams = examService.getExamsByStatus("DRAFT");
    model.addAttribute("liveExams", publishedExams);
    model.addAttribute("draftExams", unpublishedExams);
    return "teacher/dashboard";
  }

  @GetMapping("/exams")
  public String exams(Model model) {
    User currentUser = userService.getCurrentUser();
    List<Exam> exams = examService.getExamsByTeacherId(currentUser.getId());
    // List<Exam> exams = examService.getAllExams();
    
    model.addAttribute("exams", exams);
    return "teacher/exams-list";
  }

  @GetMapping("/exams/{examId}")
  public String exam(Model model, @PathVariable Long examId) {
    Optional<Exam> exam = examService.getExamById(examId);

    if (!exam.isPresent()) {
      return "redirect:/teacher/exams";
    }
    model.addAttribute("exam", exam.get());

    return "teacher/exam-detail";
  }

  @GetMapping("/exams/{examId}/questions")
  public String questions(Model model, @PathVariable Long examId) {
    model.addAttribute("questions", questionService.getQuestionsByExamId(examId));
    return "teacher/questions-list";
  }

  @GetMapping("/exams/{examId}/questions/{questionId}")
  public String question(Model model, @PathVariable Long questionId) {
    Optional<Question> question = questionService.getQuestionById(questionId);

    if (!question.isPresent()) {
      return "redirect:/teacher/exams/{examId}/questions";
    }
    model.addAttribute("question", question.get());
    return "teacher/question-detail";
  }

  @GetMapping("/students")
  public String students(Model model) {
    // List<User> students = userService.getAllStudents();
    List<User> students = userService.getAllUsers();
    model.addAttribute("students", students);
    return "teacher/students-list";
  }


  @GetMapping("/history")
  public String history(Model model) {
    return "teacher/history";
  }

  @GetMapping("/setting")
  public String setting(Model model) {
    return "teacher/setting";
  }
}
