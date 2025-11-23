package com.example.intelligenttutoringsystem.assessment.engine;

import com.example.intelligenttutoringsystem.assessment.domain.StudentProfile;
import com.example.intelligenttutoringsystem.content.domain.Question;

public interface IHintStrategy {
    String generate(Question question, String selectedChoiceId, StudentProfile profile);
}
