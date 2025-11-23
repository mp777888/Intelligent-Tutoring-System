package com.example.Intelligent.Tutoring.System.content_management.application.dto.request;

import java.util.List;

public record QuestionCreateRequest(
        Long quizId,
        String content,
        String type,
        List<String> options,
        String correctAnswer
)
{}
