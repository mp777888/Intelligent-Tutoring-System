package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Assessment;

public interface AssessmentJpaRepository extends JpaRepository<Assessment, String> {
}
