package com.example.Intelligent.Tutoring.System.content_management.adapter.persistence;


import com.example.Intelligent.Tutoring.System.content_management.adapter.jpa.JpaQuestionRepository;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.QuestionMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.QuestionRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionRepositoryAdapter implements QuestionRepositoryPort {
    JpaQuestionRepository jpaQuizRepository;
    QuestionMapper questionMapper;

    @Override
    public Question save(Question question) {
        var questionEntity = questionMapper.toEntity(question);
        var savedEntity = jpaQuizRepository.save(questionEntity);
        return questionMapper.toDomain(savedEntity);
    }
}
