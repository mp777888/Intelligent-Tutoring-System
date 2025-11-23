package com.example.intelligenttutoringsystem.assessment.application.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitAssessmentResponse {
    private String assessmentId;
    private double totalScore;
    private double maxScore;
    private List<QuestionResultResponse> items;
    private String overallFeedback;
    private Instant assessedAt;
}
