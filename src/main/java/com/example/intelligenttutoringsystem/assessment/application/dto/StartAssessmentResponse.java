package com.example.intelligenttutoringsystem.assessment.application.dto;

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
public class StartAssessmentResponse {
    private String assessmentId;
    private String attemptId;
    private List<BasicQuestionResponse> questions;
}
