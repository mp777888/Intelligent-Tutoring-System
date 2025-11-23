package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Quiz;

public interface QuizJpaRepository extends JpaRepository<Quiz, String> {
    List<Quiz> findByTopicId(String topicId);
}
