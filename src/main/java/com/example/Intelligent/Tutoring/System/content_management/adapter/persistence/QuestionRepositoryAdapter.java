package com.example.Intelligent.Tutoring.System.content_management.adapter.persistence;


import com.example.Intelligent.Tutoring.System.content_management.adapter.jpa.JpaQuestionRepository;
import com.example.Intelligent.Tutoring.System.content_management.adapter.jpa.JpaQuizRepository;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.QuestionMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.QuestionRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionRepositoryAdapter implements QuestionRepositoryPort {
    JpaQuestionRepository jpaQuestionRepository;
    JpaQuizRepository jpaQuizRepository;
    QuestionMapper questionMapper;

    @Override
    public Question save(Question question) {
        var questionEntity = questionMapper.toEntity(question);
        var savedEntity = jpaQuestionRepository.save(questionEntity);
        return questionMapper.toDomain(savedEntity);
    }

    @Override
    public List<Question> findAllByQuizId(Long quizId) {
        var quizEntity =  jpaQuizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with id: " + quizId));

        return quizEntity.getQuestions().stream()
                .map(questionMapper::toDomain)
                .toList();

    }
}
