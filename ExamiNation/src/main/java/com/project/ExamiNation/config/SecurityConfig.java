package com.project.ExamiNation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                // Public routes
                .requestMatchers("/").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()

                // Role-based access
                .requestMatchers("/teacher/**").hasRole("TEACHER") // Only TEACHER can access /teacher/**
                .requestMatchers("/student/**").hasRole("STUDENT") // Only STUDENT can access /student/**

                // Authenticated routes (any authenticated user)
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/setting/profile/update").authenticated()
                .requestMatchers("/setting/password/update").authenticated()

                // Deny all other requests
                .anyRequest().denyAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
            )
            .logout(config -> config
                .logoutSuccessUrl("/")
            )
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}