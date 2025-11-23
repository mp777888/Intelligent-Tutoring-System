package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Quiz;

public interface QuizJpaRepository extends JpaRepository<Quiz, String> {
}
