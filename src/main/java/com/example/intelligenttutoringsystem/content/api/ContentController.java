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
import com.example.intelligenttutoringsystem.content.application.dto.ContentMapper;
import com.example.intelligenttutoringsystem.content.application.dto.QuestionResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final QuestionService questionService;
    private final ContentMapper contentMapper;

    @PostMapping("/questions")
    public QuestionResponse createQuestion(@RequestBody CreateQuestionRequest request) {
        var created = questionService.createQuestion(request);
        return contentMapper.toQuestionResponse(created);
    }

    @GetMapping("/questions/{id}")
    public QuestionResponse getQuestion(@PathVariable String id) {
        var question = questionService.getQuestion(id);
        return contentMapper.toQuestionResponse(question);
    }

    @PutMapping("/questions/{id}")
    public QuestionResponse updateQuestion(@PathVariable String id, @RequestBody UpdateQuestionRequest req) {
        var updated = questionService.updateQuestion(id, req);
        return contentMapper.toQuestionResponse(updated);
    }

    @DeleteMapping("/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/questions")
    public List<QuestionResponse> getQuestions(
            @RequestParam(required = false) String topicId,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        if (topicId != null || difficulty != null || limit != null) {
            var questions = questionService.getQuestionsForAssessment(topicId, difficulty, limit != null ? limit : 100);
            return questions.stream().map(contentMapper::toQuestionResponse).toList();
        }
        var questions = questionService.listQuestions(page, size);
        return questions.stream().map(contentMapper::toQuestionResponse).toList();
    }
}
