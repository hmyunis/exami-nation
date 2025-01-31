package com.project.ExamiNation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ExamiNation.model.Exam;
import com.project.ExamiNation.model.ExamResult;
import com.project.ExamiNation.model.ExamSession;
import com.project.ExamiNation.model.Question;
import com.project.ExamiNation.model.User;
import com.project.ExamiNation.model.dto.DashboardExamSessionDto;
import com.project.ExamiNation.model.dto.ExamDetailDto;
import com.project.ExamiNation.model.dto.ExamListDto;
import com.project.ExamiNation.model.dto.StudentListDto;
import com.project.ExamiNation.service.ExamResultService;
import com.project.ExamiNation.service.ExamService;
import com.project.ExamiNation.service.ExamSessionService;
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

  @Autowired
  private ExamSessionService examSessionService;

  @Autowired
  private ExamResultService examResultService;
  
  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    List<ExamSession> openExamSessions = examSessionService.getExamSessionsByStatus("OPEN");
    List<ExamSession> closedExamSessions = examSessionService.getExamSessionsByStatus("CLOSED");

    List<DashboardExamSessionDto> openExamSessionsDtos = new ArrayList<DashboardExamSessionDto>();
    for (int i = 0; i < openExamSessions.size(); i++) {
      ExamSession examSession = openExamSessions.get(i);
      DashboardExamSessionDto dashboardExamSessionDto = new DashboardExamSessionDto(examSession.getId(), examSession.getExam(), examSession.getParticipants(), examSession.getDuration(), examSession.getStatus(), examSession.getStartTime());
      dashboardExamSessionDto.setExamDate(java.time.LocalDate.from(examSession.getStartTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
      dashboardExamSessionDto.setEndTime(dashboardExamSessionDto.getStartTime().plusMinutes(examSession.getDuration()));

      List<Question> questions = questionService.getQuestionsByExamId(examSession.getExam().getId());
      dashboardExamSessionDto.setTotalQuestions(questions.size());

      openExamSessionsDtos.add(dashboardExamSessionDto);
    }

    List<DashboardExamSessionDto> closedExamSessionsDtos = new ArrayList<DashboardExamSessionDto>();
    for (int i = 0; i < closedExamSessions.size(); i++) {
      ExamSession examSession = closedExamSessions.get(i);
      DashboardExamSessionDto dashboardExamSessionDto = new DashboardExamSessionDto(examSession.getId(), examSession.getExam(), examSession.getParticipants(), examSession.getDuration(), examSession.getStatus(), examSession.getStartTime());
      dashboardExamSessionDto.setExamDate(java.time.LocalDate.from(examSession.getStartTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
      dashboardExamSessionDto.setEndTime(dashboardExamSessionDto.getStartTime().plusMinutes(examSession.getDuration()));

      List<Question> questions = questionService.getQuestionsByExamId(examSession.getExam().getId());
      dashboardExamSessionDto.setTotalQuestions(questions.size());

      closedExamSessionsDtos.add(dashboardExamSessionDto);
    }

    List<Exam> exams = examService.getAllExams();

    model.addAttribute("allExams", exams);
    model.addAttribute("liveExamSessions", openExamSessionsDtos);
    model.addAttribute("closedExamSessions", closedExamSessionsDtos);
    return "teacher/dashboard";
  }

  @PostMapping("/exam-sessions/create")
  public String createExamSession(Model model,
        @RequestParam ("examId") Long examId,
        @RequestParam ("duration") int duration,
        @RequestParam ("status") String status,
        @RequestParam ("startTime") String formDatetime
    ) {
    java.util.Date startTime = java.util.Date.from(java.time.LocalDateTime.parse(formDatetime, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
    .atZone(java.time.ZoneId.systemDefault())
    .toInstant());

    ExamSession examSession = new ExamSession();
    examSession.setExam(examService.getExamById(examId).get());
    examSession.setDuration(duration);
    examSession.setStatus(status);
    examSession.setStartTime(startTime);
    examSession.setParticipants(new ArrayList<>());
    examSessionService.createExamSession(examSession);
    return "redirect:/teacher/dashboard";
  }

  @PostMapping("/exam-sessions/{examSessionId}/terminate")
  public String terminateExamSession(@PathVariable Long examSessionId) {
    examSessionService.updateStatusById(examSessionId, "CLOSED");
    return "redirect:/teacher/dashboard";
  }

  @GetMapping("/exams")
  public String exams(Model model) {
    User currentUser = userService.getCurrentUser();
    List<Exam> exams = examService.getExamsByTeacherId(currentUser.getId());
    // List<Exam> exams = examService.getAllExams();

    List<ExamListDto> dtos = new ArrayList<ExamListDto>();
    for (int i = 0; i < exams.size(); i++) {
      ExamListDto dto = new ExamListDto(exams.get(i).getId(), exams.get(i).getTitle(), exams.get(i).getStatus(), exams.get(i).getWeight(), exams.get(i).getCreatedAt());
      List<Question> questions = questionService.getQuestionsByExamId(exams.get(i).getId());
      dto.setTotalQuestions(questions.size());
      dtos.add(dto);
    }
    
    model.addAttribute("exams", dtos);
    return "teacher/exams-list";
  }

  @PostMapping("/exams")
  public String createExam(
      @RequestParam("title") String title,
      @RequestParam("description") String description, 
      @RequestParam("status") String status,
      @RequestParam("weight") double weight
    ) {
    Exam exam = new Exam();
    User currentUser = userService.getCurrentUser();
    exam.setTitle(title);
    exam.setDescription(description);
    exam.setStatus(status);
    exam.setWeight(weight);
    exam.setCreatedBy(currentUser);
    examService.saveExam(exam);

    return "redirect:/teacher/exams";
  }

  @GetMapping("/exams/{examId}")
  public String exam(Model model, @PathVariable Long examId) {
    Optional<Exam> exam = examService.getExamById(examId);

    if (!exam.isPresent()) {
      return "redirect:/teacher/exams";
    }

    ExamDetailDto examDetailDto = new ExamDetailDto(
      exam.get().getId(),
      exam.get().getTitle(),
      exam.get().getStatus(),
      questionService.getQuestionsByExamId(examId).size(),
      exam.get().getWeight(),
      exam.get().getCreatedAt(),
      questionService.getQuestionsByExamId(examId)
    );

    model.addAttribute("exam", examDetailDto);

    return "teacher/exam-detail";
  }

  @GetMapping("/publish/{examId}/{src}")
  public String publish(@PathVariable Long examId, @PathVariable String src) {
    examService.updateStatusById("PUBLISHED", examId);

    if ("exams-list".equals(src)) {
        return "redirect:/teacher/exams";
    } else if ("exam-detail".equals(src)) {
        return "redirect:/teacher/exams/" + examId;
    } else if ("dashboard".equals(src)) {
        return "redirect:/teacher/dashboard";
    }

    return "redirect:/teacher/exams";
  }

  @GetMapping("/unpublish/{examId}/{src}")
  public String unpublish(@PathVariable Long examId, @PathVariable String src) {
      examService.updateStatusById("DRAFT", examId);

      if ("exams-list".equals(src)) {
          return "redirect:/teacher/exams";
      } else if ("exam-detail".equals(src)) {
          return "redirect:/teacher/exams/" + examId;
      } else if ("dashboard".equals(src)) {
        return "redirect:/teacher/dashboard";
      }

      return "redirect:/teacher/exams";
  }

  @PostMapping("/exams/{examId}/edit")
  public String updateExam(
        @PathVariable Long examId,
        @RequestParam("title") String title,
        @RequestParam("status") String status,
        @RequestParam("weight") double weight
    ) {
    Exam exam = examService.getExamById(examId).get();
    exam.setTitle(title);
    exam.setStatus(status);
    exam.setWeight(weight);
    examService.saveExam(exam);
    return "redirect:/teacher/exams/" + examId;
  }

  @GetMapping("/exams/{examId}/delete")
  public String deleteExam(@PathVariable Long examId) {
    examService.deleteExam(examId);
    return "redirect:/teacher/exams";
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

  @PostMapping("/questions/{examId}")
  public String createQuestion(
            @PathVariable Long examId,
            @RequestParam("content") String content,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("option4") String option4,
            @RequestParam("correctAnswer") int correctAnswer
    ) {
    Question question = new Question();
    question.setContent(content);
    question.setOption1(option1);
    question.setOption2(option2);
    question.setOption3(option3);
    question.setOption4(option4);
    question.setCorrectAnswer(correctAnswer);
    question.setExam(examService.getExamById(examId).get());
    questionService.saveQuestion(question);

    return "redirect:/teacher/exams/" + examId;
 }

  @PostMapping("/questions/{questionId}/edit")
  public String updateQuestion(
            @PathVariable Long questionId,
            @RequestParam("content") String content,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("option4") String option4,
            @RequestParam("correctAnswer") int correctAnswer
        ) {
        Question question = questionService.getQuestionById(questionId).get();

        question.setContent(content);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectAnswer(correctAnswer);

        questionService.saveQuestion(question);

        return "redirect:/teacher/exams/" + question.getExam().getId();
  }

  @GetMapping("/questions/{questionId}/delete")
  public String deleteQuestion(@PathVariable Long questionId) {
    Long examId = questionService.getQuestionById(questionId).get().getExam().getId();
    questionService.deleteQuestion(questionId);
    return "redirect:/teacher/exams/" + examId;
  }

  @GetMapping("/students")
  public String students(Model model) {
    List<User> students = userService.getAllStudents();
    // List<User> students = userService.getAllUsers();

    List<StudentListDto> studentListDto = new ArrayList<StudentListDto>();
    
    for (int i = 0; i < students.size(); i++) {
      StudentListDto dto = new StudentListDto();
      dto.setId(students.get(i).getId());
      dto.setFullName(students.get(i).getFullName());
      dto.setUsername(students.get(i).getUsername());
      dto.setEmail(students.get(i).getEmail());
      dto.setRole(students.get(i).getRole());
      dto.setDateJoined(students.get(i).getDateJoined());

      dto.setTotalMarks(0);
      List<ExamResult> examResult = examResultService.getResultsByStudent(students.get(i).getId());
      for (int j = 0; j < examResult.size(); j++) {
        dto.setTotalMarks(dto.getTotalMarks() + examResult.get(j).getScore());
      }

      studentListDto.add(dto);
    }

    model.addAttribute("students", studentListDto);
    return "teacher/students-list";
  }

  @PostMapping("/students/{studentId}/delete")
  public String deleteStudent(@PathVariable Long studentId) {
    userService.deleteUser(studentId);
    return "redirect:/teacher/students";
  }


  @GetMapping("/history")
  public String history(Model model) {
    return "teacher/history";
  }

  @GetMapping("/setting")
  public String setting(Model model) {
    User user = userService.getCurrentUser();
    model.addAttribute("user", user);
    return "teacher/setting";
  }
}
