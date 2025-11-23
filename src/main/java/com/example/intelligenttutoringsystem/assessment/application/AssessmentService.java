package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Assessment;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;
import com.example.intelligenttutoringsystem.assessment.engine.IAssessmentEngine;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AssessmentJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;
import com.example.intelligenttutoringsystem.content.application.QuestionService;
import com.example.intelligenttutoringsystem.content.domain.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentJpaRepository assessmentRepo;
    private final AttemptJpaRepository attemptRepo;
    private final QuestionService questionService;
    private final IAssessmentEngine assessmentEngine;

    @Transactional
    public Map<String, Object> startAssessment(StartAssessmentRequest req) {
        var questions = questionService.getQuestionsForAssessment(
                req.topicId(), req.difficulty(), req.numQuestions());

        Assessment assessment = Assessment.builder()
                .studentId(req.studentId())
                .topicId(req.topicId())
                .numQuestions(req.numQuestions())
                .createdAt(Instant.now())
                .build();

        assessment = assessmentRepo.save(assessment);

        Attempt attempt = Attempt.builder()
                .assessment(assessment)
                .studentId(req.studentId())
                .startedAt(Instant.now())
                .totalScore(0.0)
                .maxScore((double) questions.size())
                .build();

        List<AttemptItem> items = new ArrayList<>();
        for (Question q : questions) {
            AttemptItem item = AttemptItem.builder()
                    .attempt(attempt)
                    .questionId(q.getId())
                    .selectedChoiceId(null)
                    .isCorrect(null)
                    .score(0.0)
                    .feedback(null)
                    .build();
            items.add(item);
        }
        attempt.setItems(items);
        attempt = attemptRepo.save(attempt);

        Map<String, Object> response = new HashMap<>();
        response.put("assessmentId", assessment.getId());
        response.put("attemptId", attempt.getId());
        response.put("questions", questions);
        return response;
    }

    @Transactional
    public Map<String, Object> submitAssessment(SubmitAssessmentRequest req) {
        Attempt attempt = attemptRepo.findByAssessmentId(req.assessmentId());
        if (attempt == null) {
            throw new IllegalArgumentException("Assessment not found");
        }

        var result = assessmentEngine.assess(req.studentId(), req.answers());

        Map<String, Object> resp = new HashMap<>();
        resp.put("assessmentId", req.assessmentId());
        resp.put("totalScore", result.totalScore());
        resp.put("maxScore", result.maxScore());
        resp.put("items", result.items());
        resp.put("overallFeedback", result.overallFeedback());
        return resp;
    }
}
