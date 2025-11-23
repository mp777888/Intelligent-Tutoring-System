package com.example.intelligenttutoringsystem.assessment.application.dto;

import java.util.List;

public record SubmitAssessmentRequest(
        String assessmentId,
        List<AnswerDto> answers) {
}