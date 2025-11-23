package com.example.intelligenttutoringsystem.content.api;

import com.example.intelligenttutoringsystem.content.application.CreateQuestionRequest;
import com.example.intelligenttutoringsystem.content.application.QuestionService;
import com.example.intelligenttutoringsystem.content.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public Question createQuestion(@RequestBody CreateQuestionRequest request) {
        return questionService.createQuestion(request);
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(
            @RequestParam(required = false) String topicId,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(defaultValue = "10") int limit) {
        return questionService.getQuestionsForAssessment(topicId, difficulty, limit);
    }
}
