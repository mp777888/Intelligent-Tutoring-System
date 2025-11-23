package com.example.intelligenttutoringsystem.assessment.api.dto;

import java.time.Instant;
import java.util.List;

import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;

public record AttemptDto(
    String id,
    String assessmentId,
    String studentId,
    Instant startedAt,
    Instant finishedAt,
    Double totalScore,
    Double maxScore,
    List<AttemptItem> items
) {
    public static AttemptDto from(com.example.intelligenttutoringsystem.assessment.domain.Attempt attempt) {
        return new AttemptDto(
            attempt.getId(),
            attempt.getAssessment() != null ? attempt.getAssessment().getId() : null,
            attempt.getStudentId(),
            attempt.getStartedAt(),
            attempt.getFinishedAt(),
            attempt.getTotalScore(),
            attempt.getMaxScore(),
            attempt.getItems()
        );
    }
}
