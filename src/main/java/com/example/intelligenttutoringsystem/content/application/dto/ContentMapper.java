package com.example.intelligenttutoringsystem.content.application.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.intelligenttutoringsystem.content.domain.Choice;
import com.example.intelligenttutoringsystem.content.domain.Course;
import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.domain.Topic;

@Component
public class ContentMapper {

    public CourseResponse toCourseResponse(Course course) {
        if (course == null)
            return null;

        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .version(course.getVersion())
                .validFrom(course.getValidFrom())
                .validTo(course.getValidTo())
                .topics(course.getTopics() != null ? course.getTopics().stream().map(this::toTopicResponse).toList()
                        : List.of())
                .build();
    }

    public TopicResponse toTopicResponse(Topic topic) {
        if (topic == null)
            return null;

        return TopicResponse.builder()
                .id(topic.getId())
                .courseId(topic.getCourse() != null ? topic.getCourse().getId() : null)
                .name(topic.getName())
                .description(topic.getDescription())
                .createdAt(topic.getCreatedAt())
                .updatedAt(topic.getUpdatedAt())
                .version(topic.getVersion())
                .validFrom(topic.getValidFrom())
                .validTo(topic.getValidTo())
                .questions(topic.getQuestions() != null
                        ? topic.getQuestions().stream().map(this::toQuestionResponse).toList()
                        : List.of())
                .build();
    }

    public QuestionResponse toQuestionResponse(Question question) {
        if (question == null)
            return null;

        return QuestionResponse.builder()
                .id(question.getId())
                .content(question.getContent())
                .difficulty(question.getDifficulty())
                .questionType(question.getQuestionType())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .version(question.getVersion())
                .validFrom(question.getValidFrom())
                .validTo(question.getValidTo())
                .choices(question.getChoices() != null
                        ? question.getChoices().stream().map(this::toChoiceResponse).toList()
                        : List.of())
                .build();
    }

    public ChoiceResponse toChoiceResponse(Choice choice) {
        if (choice == null)
            return null;

        return ChoiceResponse.builder()
                .id(choice.getId())
                .content(choice.getContent())
                .correct(choice.isCorrect())
                .build();
    }
}
