package com.example.Intelligent.Tutoring.System.content_management.application.mapper;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuestionEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuestionResponse;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
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
                question.getOptions(),
                question.getType()
        );
    }

    public Question toDomain(QuestionEntity questionEntity){
        return new Question(
                questionEntity.getId(),
                questionEntity.getContent(),
                questionEntity.getCorrectAnswer(),
                questionEntity.getOptions(),
                questionEntity.getType()
        );
    }
}
