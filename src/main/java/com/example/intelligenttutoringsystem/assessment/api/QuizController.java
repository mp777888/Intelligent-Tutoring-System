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
import com.example.intelligenttutoringsystem.assessment.domain.Quiz;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> create(@RequestBody CreateQuizRequest req) {
        Quiz created = quizService.createQuiz(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public Quiz get(@PathVariable String id) {
        return quizService.getQuiz(id);
    }

    @GetMapping
    public List<Quiz> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return quizService.listQuizzes(page, size);
    }

    @PutMapping("/{id}")
    public Quiz update(@PathVariable String id, @RequestBody UpdateQuizRequest req) {
        return quizService.updateQuiz(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        quizService.deleteQuiz(id);
    }
}
