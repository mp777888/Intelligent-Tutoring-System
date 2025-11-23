package com.example.Intelligent.Tutoring.System.content_management.adapter.rest;

import com.example.Intelligent.Tutoring.System.content_management.application.dto.request.QuestionCreateRequest;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.QuestionResponse;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.QuestionMapper;
import com.example.Intelligent.Tutoring.System.content_management.application.service.impl.QuestionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QuestionController {
    QuestionServiceImpl questionService;
    QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@RequestBody QuestionCreateRequest request) {
        var command = new QuestionServiceImpl.AddQuestionCommand(
                request.quizId(),
                request.content(),
                request.type(),
                request.options(),
                request.correctAnswer()
        );

        var question = questionService.addQuestion(command);
        return ResponseEntity.ok(questionMapper.toResponse(question));
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getQuestionsByQuizId(@RequestParam Long quizId) {
        var command = new QuestionServiceImpl.GetQuestionsByQuizIdCommand(quizId);
        var questions = questionService.getAllQuestions(command);
        var response = questions.stream()
                .map(questionMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}
