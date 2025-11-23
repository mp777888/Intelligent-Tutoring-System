package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.intelligenttutoringsystem.content.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.AnswerDto;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Assessment;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AssessmentJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;
import com.example.intelligenttutoringsystem.content.application.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentJpaRepository assessmentRepo;
    private final AttemptJpaRepository attemptRepo;
    private final QuestionService questionService;

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

        double totalScore = 0.0;

        for (AttemptItem item : attempt.getItems()) {
            AnswerDto ans = req.answers().stream()
                    .filter(a -> a.questionId().equals(item.getQuestionId()))
                    .findFirst()
                    .orElse(null);

            if (ans == null)
                continue;

            Question q = questionService.getQuestionsForAssessment(
                    null, null, Integer.MAX_VALUE).stream().filter(qq -> qq.getId().equals(item.getQuestionId()))
                    .findFirst()
                    .orElse(null);

            if (q == null)
                continue;

            item.setSelectedChoiceId(ans.selectedChoiceId());

            boolean isCorrect = q.getChoices().stream()
                    .anyMatch(c -> c.getId().equals(ans.selectedChoiceId()) && c.isCorrect());

            double score = isCorrect ? 1.0 : 0.0;

            item.setIsCorrect(isCorrect);
            item.setScore(score);
            item.setFeedback(isCorrect ? "Đúng rồi!" : "Sai, hãy xem lại kiến thức.");

            totalScore += score;
        }

        attempt.setFinishedAt(Instant.now());
        attempt.setTotalScore(totalScore);
        attemptRepo.save(attempt);

        Map<String, Object> result = new HashMap<>();
        result.put("assessmentId", attempt.getAssessment().getId());
        result.put("totalScore", attempt.getTotalScore());
        result.put("maxScore", attempt.getMaxScore());
        result.put("items", attempt.getItems());
        return result;
    }
}
