package com.example.intelligenttutoringsystem.assessment.application.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.intelligenttutoringsystem.assessment.domain.Quiz;
import com.example.intelligenttutoringsystem.assessment.domain.QuizQuestion;
import com.example.intelligenttutoringsystem.content.application.dto.ContentMapper;
import com.example.intelligenttutoringsystem.content.domain.Topic;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuizMapper {

    private final ContentMapper contentMapper;

    public QuizResponse toQuizResponse(Quiz quiz) {
        if (quiz == null) return null;
        
        Topic topic = quiz.getTopic();
        
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        List<com.example.intelligenttutoringsystem.content.application.dto.QuestionResponse> selectedQuestions = 
            quizQuestions != null ? 
                quizQuestions.stream()
                    .map(qq -> contentMapper.toQuestionResponse(qq.getQuestion()))
                    .toList() : 
                List.of();
        
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .topicId(topic != null ? topic.getId() : null)
                .topicName(topic != null ? topic.getName() : null)
                .createdAt(quiz.getCreatedAt())
                .updatedAt(quiz.getUpdatedAt())
                .questions(selectedQuestions)
                .build();
    }
}
