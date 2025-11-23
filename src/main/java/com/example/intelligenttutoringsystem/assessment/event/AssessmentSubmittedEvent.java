package com.example.intelligenttutoringsystem.assessment.event;

import java.time.Instant;

import com.example.intelligenttutoringsystem.assessment.engine.AssessmentResult;

public record AssessmentSubmittedEvent(
                String studentId,
                AssessmentResult result,
                Instant occurredAt) {
}
