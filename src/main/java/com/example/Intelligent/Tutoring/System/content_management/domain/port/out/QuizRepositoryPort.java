package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;

public interface QuizRepositoryPort {
    Quiz save(Quiz quiz);
}
