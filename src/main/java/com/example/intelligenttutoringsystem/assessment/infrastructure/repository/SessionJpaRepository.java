package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Session;

public interface SessionJpaRepository extends JpaRepository<Session, String> {
    Optional<Session> findByQuizIdAndStudentIdAndCompletedFalse(String quizId, String studentId);
}
