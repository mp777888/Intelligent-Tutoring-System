package com.example.intelligenttutoringsystem.assessment.application;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.Assessment;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AssessmentJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptJpaRepository attemptRepo;
    private final AssessmentJpaRepository assessmentRepo;

    @Transactional(readOnly = true)
    public Attempt getAttempt(@NonNull String id) {
        return attemptRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found"));
    }

    @Transactional(readOnly = true)
    public List<Attempt> listAttempts(int page, int size) {
        return attemptRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional(readOnly = true)
    public List<Attempt> listByStudent(String studentId) {
        return attemptRepo.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Attempt> listByAssessment(String assessmentId) {
        return attemptRepo.findByAssessment_Id(assessmentId);
    }

    @Transactional(readOnly = true)
    public List<Attempt> listByStudentAndAssessment(String studentId, String assessmentId) {
        Assessment assessment = assessmentRepo.findById(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assessment not found"));
        return attemptRepo.findByAssessmentAndStudentId(assessment, studentId);
    }
}
