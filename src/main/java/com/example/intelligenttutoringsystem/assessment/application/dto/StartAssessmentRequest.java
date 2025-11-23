package com.example.intelligenttutoringsystem.assessment.application.dto;

public record StartAssessmentRequest(
        String studentId,
        String topicId,
        Integer difficulty,
        int numQuestions) {
}
