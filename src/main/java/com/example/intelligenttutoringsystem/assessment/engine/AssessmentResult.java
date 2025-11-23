package com.example.intelligenttutoringsystem.assessment.engine;

import java.time.Instant;
import java.util.List;

import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;

public record AssessmentResult(
                String id,
                String studentId,
                double totalScore,
                double maxScore,
                List<AttemptItem> items,
                String overallFeedback,
                Instant assessedAt) {
}
