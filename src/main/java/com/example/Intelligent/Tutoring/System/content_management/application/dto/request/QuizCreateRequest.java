package com.example.Intelligent.Tutoring.System.content_management.application.dto.request;

import java.time.LocalDateTime;

public record QuizCreateRequest(
        Long courseId,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime closedAt
) {}
