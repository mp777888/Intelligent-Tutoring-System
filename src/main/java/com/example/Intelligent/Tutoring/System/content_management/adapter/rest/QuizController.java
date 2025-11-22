package com.example.Intelligent.Tutoring.System.content_management.adapter.rest;

import com.example.Intelligent.Tutoring.System.content_management.application.dto.request.QuizCreateRequest;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuestionResponse;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuizResponse;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.QuizMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.IQuizUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QuizController {
    final IQuizUseCase quizUseCase;
    final QuizMapper quizMapper;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizCreateRequest request) {
        var command = new IQuizUseCase.CreateQuizCommand(
                request.courseId(),
                request.title(),
                request.description(),
                request.createdAt(),
                request.closedAt()
        );

        var quiz = quizUseCase.createQuiz(command);
        return ResponseEntity.ok(quizMapper.toResponse(quiz));
    }

}
