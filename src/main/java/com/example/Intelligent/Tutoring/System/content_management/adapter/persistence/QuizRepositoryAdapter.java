package com.example.Intelligent.Tutoring.System.content_management.adapter.persistence;


import com.example.Intelligent.Tutoring.System.content_management.adapter.jpa.JpaQuizRepository;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.QuizMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.QuizRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizRepositoryAdapter implements QuizRepositoryPort {
    JpaQuizRepository jpaQuizRepository;
    QuizMapper quizMapper;

    @Override
    public Quiz save(Quiz quiz) {
        var quizEntity = quizMapper.toEntity(quiz);
        var savedEntity = jpaQuizRepository.save(quizEntity);
        return quizMapper.toDomain(savedEntity);
    }

    @Override
    public Quiz getById(Long quizId) {
        var quizEntity = jpaQuizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with id: " + quizId));
        return quizMapper.toDomain(quizEntity);
    }

    @Override
    public List<Quiz> findAllByCourseId(Long courseId) {
        var quizEntities = jpaQuizRepository.findAllByCourseId(courseId);
        return quizEntities.stream()
                .map(quizMapper::toDomain)
                .toList();
    }

    @Override
    public Boolean exitstById(Long quizId) {
        return jpaQuizRepository.existsById(quizId);
    }
}
