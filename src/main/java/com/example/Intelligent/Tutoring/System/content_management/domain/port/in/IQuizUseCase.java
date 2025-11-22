package com.example.Intelligent.Tutoring.System.content_management.domain.port.in;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;

import java.time.LocalDateTime;

public interface IQuizUseCase {
    Quiz createQuiz(CreateQuizCommand command);
    record CreateQuizCommand(Long courseId, String title, String description, LocalDateTime createdAt, LocalDateTime closedAt) {}
}
