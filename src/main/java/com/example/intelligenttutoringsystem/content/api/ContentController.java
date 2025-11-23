package com.example.intelligenttutoringsystem.content.api;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.example.intelligenttutoringsystem.content.application.CreateQuestionRequest;
import com.example.intelligenttutoringsystem.content.application.QuestionService;
import com.example.intelligenttutoringsystem.content.application.UpdateQuestionRequest;
import com.example.intelligenttutoringsystem.content.domain.Question;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public Question createQuestion(@RequestBody CreateQuestionRequest request) {
        return questionService.createQuestion(request);
    }

    @GetMapping("/questions/{id}")
    public Question getQuestion(@PathVariable String id) {
        return questionService.getQuestion(id);
    }

    @PutMapping("/questions/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody UpdateQuestionRequest req) {
        return questionService.updateQuestion(id, req);
    }

    @DeleteMapping("/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(
            @RequestParam(required = false) String topicId,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        if (topicId != null || difficulty != null || limit != null) {
            return questionService.getQuestionsForAssessment(topicId, difficulty, limit != null ? limit : 100);
        }
        return questionService.listQuestions(page, size);
    }
}
