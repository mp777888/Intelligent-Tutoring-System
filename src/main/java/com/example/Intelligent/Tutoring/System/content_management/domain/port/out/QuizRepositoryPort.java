package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;

import java.util.List;

public interface QuizRepositoryPort {
    Quiz save(Quiz quiz);
    List<Quiz> findAllByCourseId(Long courseId);
}
