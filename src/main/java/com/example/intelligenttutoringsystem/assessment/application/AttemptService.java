package com.example.intelligenttutoringsystem.assessment.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptJpaRepository attemptRepo;

    @Transactional(readOnly = true)
    public Attempt getAttempt(String id) {
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
        Attempt single = attemptRepo.findByAssessmentId(assessmentId);
        return single != null ? List.of(single) : List.of();
    }
}
