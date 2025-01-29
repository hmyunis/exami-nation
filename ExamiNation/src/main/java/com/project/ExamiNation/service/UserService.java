package com.project.ExamiNation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ExamiNation.model.User;
import com.project.ExamiNation.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  public User getCurrentUser() {
    // Get the authentication object
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null && authentication.isAuthenticated()) {
        // Get the email of the currently logged-in user
        String email = authentication.getName();
        // Load the user from the database using the email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null); // Return the user if present, otherwise return null
    }
    return null;
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
