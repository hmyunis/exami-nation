package com.project.ExamiNation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ExamiNation.model.User;
import com.project.ExamiNation.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  public User getCurrentUser() {
    // Get the authentication object from Spring Security context
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication == null || !authentication.isAuthenticated()) {
        throw new UsernameNotFoundException("No authenticated user found");
    }

    // Extract the email from the authentication principal
    String email = authentication.getName();

    // Fetch the user from your database
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
    }

  // Create or Update
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  // Read
  public List<User> getAllUsers() {
      return userRepository.findAll();
  }

  public List<User> getAllStudents() {
      return userRepository.findByRole("STUDENT");
  }

  public Optional<User> getUserById(Long id) {
      return userRepository.findById(id);
  }

  public Optional<User> getUserByUsername(String username) {
      return userRepository.findByUsername(username);
  }

  // Delete
  public void deleteUser(Long id) {
      userRepository.deleteById(id);
  }

  // change password
  public void changePassword(String oldPassword, String newPassword) {
      User user = getCurrentUser();
        
      // Verify old password matches
      if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
          throw new BadCredentialsException("Invalid current password");
      }
        
      // Encode and set new password
      user.setPassword(passwordEncoder.encode(newPassword));
      userRepository.save(user);
  }

  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
  
}
