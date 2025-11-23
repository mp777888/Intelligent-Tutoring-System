package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;

import java.util.List;

public interface QuestionRepositoryPort {
    Question save(Question question);
    List<Question> findAllByQuizId(Long id);
}
