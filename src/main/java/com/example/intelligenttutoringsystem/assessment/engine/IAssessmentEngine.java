package com.example.intelligenttutoringsystem.assessment.engine;

import java.util.List;

import com.example.intelligenttutoringsystem.assessment.application.dto.AnswerDto;

public interface IAssessmentEngine {
    AssessmentResult assess(List<AnswerDto> answers);
}
