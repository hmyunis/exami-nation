package com.project.ExamiNation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ExamiNation.model.ExamResult;
import com.project.ExamiNation.model.ExamSession;
import com.project.ExamiNation.model.Question;
import com.project.ExamiNation.model.StudentAnswer;
import com.project.ExamiNation.model.User;
import com.project.ExamiNation.model.dto.DashboardExamSessionDto;
import com.project.ExamiNation.model.dto.ExamSubmissionDto;
import com.project.ExamiNation.service.ExamResultService;
import com.project.ExamiNation.service.ExamSessionService;
import com.project.ExamiNation.service.QuestionService;
import com.project.ExamiNation.service.StudentAnswerService;
import com.project.ExamiNation.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private UserService userService;
  
  @Autowired
  private ExamSessionService examSessionService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private StudentAnswerService studentAnswerService;

  @Autowired
  private ExamResultService examResultService;

  @GetMapping("/dashboard")
  public String getExamSessions(Model model) {
    User user = userService.getCurrentUser();
    List<ExamSession> openExamSessions = examSessionService.getExamSessionsByStatus("OPEN");

    // exclude the session if the student has got a result on the session
    List<ExamResult> results = examResultService.getResultsByStudent(user.getId());
    for (int i = 0; i < results.size(); i++) {
      ExamResult result = results.get(i);
      if (result.getExamSession().getParticipants().contains(user)) {
        openExamSessions.remove(result.getExamSession());
      }
    }

    List<DashboardExamSessionDto> openExamSessionsDtos = new ArrayList<DashboardExamSessionDto>();
    for (int i = 0; i < openExamSessions.size(); i++) {
      ExamSession examSession = openExamSessions.get(i);
      DashboardExamSessionDto dashboardExamSessionDto = new DashboardExamSessionDto(examSession.getId(), examSession.getExam(), examSession.getParticipants(), examSession.getDuration(), examSession.getStatus(), examSession.getStartTime());
      dashboardExamSessionDto.setExamDate(java.time.LocalDate.from(examSession.getStartTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
      dashboardExamSessionDto.setEndTime(dashboardExamSessionDto.getStartTime().plusMinutes(examSession.getDuration()));

      List<Question> questions = questionService.getQuestionsByExamId(examSession.getExam().getId());
      dashboardExamSessionDto.setTotalQuestions(questions.size());

      dashboardExamSessionDto.setIsParticipant(examSession.getParticipants().contains(user));

      openExamSessionsDtos.add(dashboardExamSessionDto);
    }

    model.addAttribute("student", user);
    model.addAttribute("liveExamSessions", openExamSessionsDtos);
    return "student/dashboard";
  }
  
  @PostMapping("/exam-sessions/{examSessionId}/join/{studentId}")
  public String joinExamSession(@PathVariable Long examSessionId, @PathVariable Long studentId, Model model) {
      ExamSession session = examSessionService.getExamSessionById(examSessionId);
      User student = userService.getUserById(studentId).get();
      // don't add if the student is already there
      if (!session.getParticipants().contains(student)) {
        session.getParticipants().add(student);
        examSessionService.updateExamSession(examSessionId, session);
      }
      model.addAttribute("questions", questionService.getQuestionsByExamId(session.getExam().getId()));
      model.addAttribute("examSessionId", session.getId());
      model.addAttribute("endTime", new java.util.Date(session.getStartTime().getTime() + session.getDuration() * 60 * 1000));
      return "student/exam-page";
  }

  @PostMapping("/exam/submit")
  public ResponseEntity<String> submitExam(@RequestBody ExamSubmissionDto submissionData) {
      Long examSessionId = submissionData.getExamSessionId();

      // check if the time has passed or not
      ExamSession session = examSessionService.getExamSessionById(examSessionId);
      if (session.getStartTime().after(new java.util.Date())) {
          return ResponseEntity.badRequest().body("The exam has not started yet!");
      } else if (session.getStartTime().after(new java.util.Date(session.getStartTime().getTime() + session.getDuration() * 60 * 1000))) {
          return ResponseEntity.badRequest().body("The exam has already ended!");
      } else if (session.getStatus().equals("CLOSED")) {
          return ResponseEntity.badRequest().body("The exam has already been closed!");
      }
      
      // remove the last item on the answers list
      for (ExamSubmissionDto.AnswerDto answer : submissionData.getAnswers().subList(0, submissionData.getAnswers().size() - 1)) {
          // Create a new StudentAnswer entity
          StudentAnswer studentAnswer = new StudentAnswer();
          studentAnswer.setExamSession(examSessionService.getExamSessionById(examSessionId));
          studentAnswer.setStudent(userService.getCurrentUser());
          studentAnswer.setQuestion(questionService.getQuestionById(answer.getQuestionId()).get());
          studentAnswer.setSelectedAnswer(answer.getSelectedAnswer());

          studentAnswerService.saveStudentAnswer(studentAnswer);
      }

      // Calculate the total score of the student
      double totalScore = 0;
      for (int i = 0; i < submissionData.getAnswers().size() - 1; i++) {
          StudentAnswer studentAnswer = studentAnswerService.getAnswersByExamSessionAndStudent(examSessionId, userService.getCurrentUser().getId()).get(i);
          if (studentAnswer.getQuestion().getCorrectAnswer() == studentAnswer.getSelectedAnswer()) {
              totalScore += studentAnswer.getExamSession().getExam().getWeight() / studentAnswer.getQuestion().getExam().getQuestions().size();
          }
      }

      // Save the result of the student as well
      ExamResult examResult = new ExamResult();
      examResult.setExamSession(examSessionService.getExamSessionById(examSessionId));
      examResult.setStudent(userService.getCurrentUser());
      examResult.setScore(totalScore);

      examResultService.saveExamResult(examResult);

      return ResponseEntity.ok("Exam submitted successfully!");
  }
  


  @GetMapping("/history")
  public String history(Model model) {
    return "student/history";
  }

  @GetMapping("/setting")
  public String setting(Model model) {
    User user = userService.getCurrentUser();
    model.addAttribute("user", user);
    return "student/setting";
  }
}
