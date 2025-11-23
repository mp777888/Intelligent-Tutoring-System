package com.example.intelligenttutoringsystem.content.application;

import java.util.List;

public record CreateQuestionRequest(
        String topicId,
        String content,
        Integer difficulty,
        List<ChoiceRequest> choices) {
    public record ChoiceRequest(String content, boolean isCorrect) {
    }
}
