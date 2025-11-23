package com.example.intelligenttutoringsystem.assessment.engine;

import com.example.intelligenttutoringsystem.content.domain.Question;

public interface IScoringStrategy {
    double score(Question question, String selectedChoiceId);
}
