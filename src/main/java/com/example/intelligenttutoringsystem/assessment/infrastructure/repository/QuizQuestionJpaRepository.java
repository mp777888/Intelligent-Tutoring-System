package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.QuizQuestion;

public interface QuizQuestionJpaRepository extends JpaRepository<QuizQuestion, String> {
}
