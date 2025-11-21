package com.example.Intelligent.Tutoring.System.content_management.application.dto.request;

public record CourseCreateRequest(
        String title,
        String description,
        String subject
) {}