package com.example.Intelligent.Tutoring.System.content_management.application.service.impl;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.IQuizUseCase;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.CourseRepositoryPort;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.QuizRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizServiceImpl implements IQuizUseCase {

    final QuizRepositoryPort quizRepository;
    final CourseRepositoryPort courseRepository;

    @Override
    public Quiz createQuiz(CreateQuizCommand command) {
        log.info("Creating new quiz with title: {}", command.title());

        var course = courseRepository.findById(command.courseId());
        if (course == null) {
            log.warn("Course not found with id: {}", command.courseId());
            throw new IllegalArgumentException("Course not found with id: " + command.courseId());
        }
        var quiz = Quiz.builder()
                .title(command.title())
                .course(course)
                .description(command.description())
                .createdAt(command.createdAt())
                .closedAt(command.closedAt())
                .build();

        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzesByCourse(Long courseId) {
        log.info("Fetching all quizzes for course id: {}", courseId);
        return quizRepository.findAllByCourseId(courseId);
    }
}
