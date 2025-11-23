package com.example.intelligenttutoringsystem.assessment.engine.impl;

import org.springframework.stereotype.Component;

import com.example.intelligenttutoringsystem.assessment.engine.IScoringStrategy;
import com.example.intelligenttutoringsystem.content.domain.Question;

@Component
public class SimpleScoringStrategy implements IScoringStrategy {
    @Override
    public double score(Question question, String selectedChoiceId) {
        boolean isCorrect = question.getChoices().stream()
                .anyMatch(c -> c.getId().equals(selectedChoiceId) && c.isCorrect());
        return isCorrect ? 1.0 : 0.0;
    }
}
