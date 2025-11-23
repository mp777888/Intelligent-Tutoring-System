package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.Assessment;

public interface AttemptJpaRepository extends JpaRepository<Attempt, String> {

    List<Attempt> findByAssessment_Id(String assessmentId);

    List<Attempt> findByStudentId(String studentId);

    List<Attempt> findByAssessmentAndStudentId(Assessment assessment, String studentId);
}
