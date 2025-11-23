package com.example.intelligenttutoringsystem.content.application;

import java.util.List;

public record UpdateQuestionRequest(String topicId, String content, Integer difficulty,
        List<CreateQuestionRequest.ChoiceRequest> choices) {
}
