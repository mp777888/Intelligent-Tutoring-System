package com.example.intelligenttutoringsystem.assessment.engine.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.AnswerDto;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;
import com.example.intelligenttutoringsystem.assessment.engine.AssessmentResult;
import com.example.intelligenttutoringsystem.assessment.engine.IAssessmentEngine;
import com.example.intelligenttutoringsystem.assessment.engine.IHintStrategy;
import com.example.intelligenttutoringsystem.assessment.engine.IScoringStrategy;
import com.example.intelligenttutoringsystem.assessment.event.AssessmentSubmittedEvent;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;
import com.example.intelligenttutoringsystem.content.application.QuestionService;
import com.example.intelligenttutoringsystem.content.domain.Question;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultAssessmentEngine implements IAssessmentEngine {

    private final QuestionService questionService;
    private final IScoringStrategy scoringStrategy;
    private final IHintStrategy hintStrategy;
    private final AttemptJpaRepository attemptRepo;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public AssessmentResult assess(List<AnswerDto> answers) {
        Attempt attempt = Attempt.builder()
                .startedAt(Instant.now())
                .totalScore(0.0)
                .maxScore((double) answers.size())
                .build();

        List<AttemptItem> items = answers.stream().map(ans -> {
            Question q = questionService.getQuestion(ans.questionId());
            AttemptItem item = AttemptItem.builder()
                    .attempt(attempt)
                    .questionId(ans.questionId())
                    .selectedChoiceId(ans.selectedChoiceId())
                    .score(0.0)
                    .build();
            double score = scoringStrategy.score(q, ans.selectedChoiceId());
            boolean isCorrect = score > 0;
            item.setScore(score);
            item.setIsCorrect(isCorrect);
            item.setFeedback(hintStrategy.generate(q, ans.selectedChoiceId(), null));
            return item;
        }).toList();

        attempt.setItems(items);
        double totalScore = items.stream().mapToDouble(AttemptItem::getScore).sum();
        attempt.setTotalScore(totalScore);
        attempt.setFinishedAt(Instant.now());
        attemptRepo.save(attempt);

        AssessmentResult result = new AssessmentResult(
                attempt.getId(),
                null,
                totalScore,
                attempt.getMaxScore(),
                items,
                generateOverallFeedback(totalScore, attempt.getMaxScore()),
                Instant.now());

        eventPublisher.publishEvent(new AssessmentSubmittedEvent(null, result, Instant.now()));

        return result;
    }

    private String generateOverallFeedback(double totalScore, double maxScore) {
        double percent = maxScore == 0 ? 0 : (totalScore / maxScore) * 100;
        if (percent >= 90)
            return "Xuất sắc!";
        if (percent >= 70)
            return "Tốt!";
        if (percent >= 50)
            return "Khá, cần cố gắng hơn.";
        return "Cần ôn tập lại kiến thức.";
    }
}
