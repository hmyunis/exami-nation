package com.project.ExamiNation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ExamiNation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsername(String username);

}
