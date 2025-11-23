package com.example.intelligenttutoringsystem.assessment.application.dto;

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
public class QuestionResultResponse {
    private String questionId;
    private String selectedChoiceId;
    private Boolean isCorrect;
    private Double score;
    private String feedback;
}
