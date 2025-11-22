package com.example.Intelligent.Tutoring.System.content_management.application.mapper;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuizEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuizResponse;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
    private final QuestionMapper questionMapper;
    private final CourseMapper courseMapper;

    public QuizMapper(QuestionMapper questionMapper, @Lazy CourseMapper courseMapper) {
        this.questionMapper = questionMapper;
        this.courseMapper = courseMapper;
    }

    public QuizResponse toResponse(Quiz quiz){
        return new QuizResponse(
                quiz.getId(),
                quiz.getCourse().getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getQuestions().stream()
                        .map(questionMapper::toResponse)
                        .toList(),
                quiz.getCreatedAt(),
                quiz.getClosedAt()
        );
    }

    public QuizEntity toEntity(Quiz quiz){
        return new QuizEntity(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCourse() == null ? null : courseMapper.toEntity(quiz.getCourse()),
                quiz.getQuestions() == null ? java.util.Collections.emptyList() :
                quiz.getQuestions().stream()
                        .map(questionMapper::toEntity)
                        .toList(),
                quiz.getCreatedAt(),
                quiz.getClosedAt()
        );
    }

    public Quiz toDomain(QuizEntity quizEntity){
        return new Quiz(
                quizEntity.getId(),
                quizEntity.getTitle(),
                quizEntity.getDescription(),
                quizEntity.getCourse() == null ? null : courseMapper.toDomain(quizEntity.getCourse()),
                quizEntity.getQuestions() == null ? java.util.Collections.emptyList() :
                quizEntity.getQuestions().stream()
                        .map(questionMapper::toDomain)
                        .toList(),
                quizEntity.getCreatedAt(),
                quizEntity.getClosedAt()
        );
    }

}
