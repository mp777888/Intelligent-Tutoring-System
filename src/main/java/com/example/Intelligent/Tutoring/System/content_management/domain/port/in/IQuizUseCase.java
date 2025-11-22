package com.example.Intelligent.Tutoring.System.content_management.domain.port.in;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;

import java.time.LocalDateTime;
import java.util.List;

public interface IQuizUseCase {
    Quiz createQuiz(CreateQuizCommand command);
    List<Quiz> getAllQuizzesByCourse(Long courseId);
    record CreateQuizCommand(Long courseId, String title, String description, LocalDateTime createdAt, LocalDateTime closedAt) {}
}
