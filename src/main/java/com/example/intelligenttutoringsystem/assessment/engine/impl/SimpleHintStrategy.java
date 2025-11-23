package com.example.intelligenttutoringsystem.assessment.engine.impl;

import org.springframework.stereotype.Component;

import com.example.intelligenttutoringsystem.assessment.domain.StudentProfile;
import com.example.intelligenttutoringsystem.assessment.engine.IHintStrategy;
import com.example.intelligenttutoringsystem.content.domain.Question;

@Component
public class SimpleHintStrategy implements IHintStrategy {
    @Override
    public String generate(Question question, String selectedChoiceId, StudentProfile profile) {
        boolean isCorrect = question.getChoices().stream()
                .anyMatch(c -> c.getId().equals(selectedChoiceId) && c.isCorrect());

        if (isCorrect) {
            return "Đúng rồi!";
        }

        String hint = question.getChoices().stream()
                .filter(c -> c.isCorrect())
                .findFirst()
                .map(c -> "Sai rồi. Đáp án đúng là: " + c.getContent())
                .orElse("Sai rồi. Hãy xem lại kiến thức.");

        if (profile != null && profile.getTags() != null
                && profile.getTags().contains("weak-" + question.getTopic().getId())) {
            hint += " (Gợi ý: bạn thường yếu phần này, hãy ôn lại bài liên quan)";
        }
        return hint;
    }
}
