package com.project.ExamiNation.service;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
      .password(user.getPassword())
      .roles(user.getRole())
      .build();
    }
    
    return null;
  }
  
}
