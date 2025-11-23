package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Attempt;

public interface AttemptJpaRepository extends JpaRepository<Attempt, String> {

    Attempt findByAssessmentId(String assessmentId);

    List<Attempt> findByStudentId(String studentId);

    List<Attempt> findByAssessmentIdAndStudentId(String assessmentId, String studentId);
}
