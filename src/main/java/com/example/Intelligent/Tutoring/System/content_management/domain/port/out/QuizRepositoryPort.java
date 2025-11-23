package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;

import java.util.List;

public interface QuizRepositoryPort {
    Quiz save(Quiz quiz);
    Quiz getById(Long quizId);
    List<Quiz> findAllByCourseId(Long courseId);
    Boolean exitstById(Long quizId);
}
