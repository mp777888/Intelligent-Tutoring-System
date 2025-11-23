package com.example.intelligenttutoringsystem.assessment.engine;

import java.util.List;

import com.example.intelligenttutoringsystem.assessment.application.dto.AnswerDto;

public interface IAssessmentEngine {
    AssessmentResult assess(String studentId, List<AnswerDto> answers);
}
