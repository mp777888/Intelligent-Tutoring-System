package com.example.intelligenttutoringsystem.assessment.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.intelligenttutoringsystem.assessment.application.QuizService;
import com.example.intelligenttutoringsystem.assessment.application.dto.CreateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.UpdateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.QuizResponse;
import com.example.intelligenttutoringsystem.assessment.application.dto.QuizMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @PostMapping
    public ResponseEntity<QuizResponse> create(@RequestBody CreateQuizRequest req) {
        var created = quizService.createQuiz(req);
        QuizResponse response = quizMapper.toQuizResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public QuizResponse get(@PathVariable String id) {
        var quiz = quizService.getQuiz(id);
        return quizMapper.toQuizResponse(quiz);
    }

    @GetMapping
    public List<QuizResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var quizzes = quizService.listQuizzes(page, size);
        return quizzes.stream().map(quizMapper::toQuizResponse).toList();
    }

    @GetMapping("/by-topic/{topicId}")
    public List<QuizResponse> getQuizzesByTopic(@PathVariable String topicId) {
        var quizzes = quizService.getQuizzesByTopic(topicId);
        return quizzes.stream().map(quizMapper::toQuizResponse).toList();
    }

    @PutMapping("/{id}")
    public QuizResponse update(@PathVariable String id, @RequestBody UpdateQuizRequest req) {
        var updated = quizService.updateQuiz(id, req);
        return quizMapper.toQuizResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        quizService.deleteQuiz(id);
    }
}
