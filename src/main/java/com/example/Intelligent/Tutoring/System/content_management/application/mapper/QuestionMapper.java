package com.example.Intelligent.Tutoring.System.content_management.application.mapper;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuestionEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuestionResponse;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    private final QuizMapper quizMapper;

    public QuestionMapper(@Lazy QuizMapper quizMapper) {
        this.quizMapper = quizMapper;
    }

    public QuestionResponse toResponse(Question question){
        return new QuestionResponse(
                question.getId(),
                question.getContent(),
                question.getCorrectAnswer(),
                question.getOptions(),
                question.getType().toString()
        );
    }

    public QuestionEntity toEntity(Question question){
        return new QuestionEntity(
                question.getId(),
                question.getContent(),
                question.getCorrectAnswer(),
                question.getQuiz() == null ? null : quizMapper.toEntity(question.getQuiz()),
                question.getOptions(),
                question.getType()
        );
    }

    public Question toDomain(QuestionEntity questionEntity){
        return new Question(
                questionEntity.getId(),
                questionEntity.getContent(),
                questionEntity.getCorrectAnswer(),
                questionEntity.getQuiz() == null ? null : quizMapper.toDomainWithoutQuestions(questionEntity.getQuiz()),
                questionEntity.getOptions(),
                questionEntity.getType()
        );
    }
}
