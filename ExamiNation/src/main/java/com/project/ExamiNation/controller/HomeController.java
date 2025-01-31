package com.project.ExamiNation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PostMapping("/setting/profile/update")
  public String updateSetting( 
    @RequestParam String fullName,
    @RequestParam String username,
    @RequestParam String email
  ) {
    User user = userService.getCurrentUser();
    user.setFullName(fullName);
    user.setUsername(username);
    user.setEmail(email);
    userService.saveUser(user);
    if(user.getRole().equals("TEACHER")) {
      return "redirect:/teacher/setting";
    } else if(user.getRole().equals("STUDENT")) {
      return "redirect:/student/setting";
    }
    return "redirect:/dashboard";
  }

  @PostMapping("/setting/password/update")
  public String updatePassword( 
    @RequestParam String oldPassword,
    @RequestParam String newPassword,
    @RequestParam String confirmNewPassword
  ) {
    try {
        if (!newPassword.equals(confirmNewPassword)) {
            throw new BadCredentialsException("New password and confirm password do not match.");
        }
        userService.changePassword(oldPassword, newPassword);
        // Force logout after password change
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    } catch (BadCredentialsException e) {
        // Handle invalid old password
        if(userService.getCurrentUser().getRole().equals("TEACHER")) {
          return "redirect:/teacher/setting";
        } else if(userService.getCurrentUser().getRole().equals("STUDENT")) {
          return "redirect:/student/setting";
        }
        return "redirect:/dashboard";
    }
  }
}
