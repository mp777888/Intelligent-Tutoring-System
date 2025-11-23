package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.BasicQuestionResponse;
import com.example.intelligenttutoringsystem.assessment.application.dto.QuestionResultResponse;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentResponse;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentResponse;
import com.example.intelligenttutoringsystem.assessment.domain.Assessment;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;
import com.example.intelligenttutoringsystem.assessment.domain.Quiz;
import com.example.intelligenttutoringsystem.assessment.domain.QuizQuestion;
import com.example.intelligenttutoringsystem.assessment.engine.IAssessmentEngine;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AssessmentJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.AttemptJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.QuizJpaRepository;
import com.example.intelligenttutoringsystem.content.domain.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentJpaRepository assessmentRepo;
    private final AttemptJpaRepository attemptRepo;
    private final QuizJpaRepository quizRepo;
    private final IAssessmentEngine assessmentEngine;

    @Transactional
    public StartAssessmentResponse startAssessment(StartAssessmentRequest req) {
        if(req.quizId()==null || req.studentId()==null) {
            throw new IllegalArgumentException("Quiz ID and Student ID cannot be null");
        }

        Quiz quiz = quizRepo.findById(req.quizId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        if(quizQuestions==null||quizQuestions.isEmpty()) {
            throw new IllegalArgumentException("Quiz has no questions");
        }

        List<Question> questions = quizQuestions.stream()
                .map(QuizQuestion::getQuestion)
                .toList();

        Assessment assessment = Assessment.builder()
                .quizId(req.quizId())
                .topicId(quiz.getTopic().getId())
                .numQuestions(questions.size())
                .createdAt(Instant.now())
                .build();

        Assessment savedAssessment = assessmentRepo.save(assessment);

        Attempt attempt = Attempt.builder()
                .assessment(savedAssessment)
                .studentId(req.studentId())
                .startedAt(Instant.now())
                .totalScore(0.0)
                .maxScore((double) questions.size())
                .build();

        List<AttemptItem> items = new ArrayList<>();
        for(Question q : questions) {
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

        List<BasicQuestionResponse> basicQuestionResponses = 
            questions.stream()
                    .map(q -> BasicQuestionResponse.builder()
                            .id(q.getId())
                            .content(q.getContent())
                            .build())
                    .toList();

        return StartAssessmentResponse.builder()
                .assessmentId(savedAssessment.getId())
                .attemptId(attempt.getId())
                .questions(basicQuestionResponses)
                .build();
    }

    @Transactional
    public SubmitAssessmentResponse submitAssessment(SubmitAssessmentRequest req) {
        List<Attempt> attempts = attemptRepo.findByAssessment_Id(req.assessmentId());
        if (attempts == null || attempts.isEmpty()) {
            throw new IllegalArgumentException("Assessment not found");
        }
        Attempt attempt = attempts.get(0);

        var result = assessmentEngine.assess(req.answers());

        List<QuestionResultResponse> questionResults = result.items().stream()
                .map(item -> QuestionResultResponse.builder()
                        .questionId(item.getQuestionId())
                        .selectedChoiceId(item.getSelectedChoiceId())
                        .isCorrect(item.getIsCorrect())
                        .score(item.getScore())
                        .feedback(item.getFeedback())
                        .build())
                .toList();

        return SubmitAssessmentResponse.builder()
                .assessmentId(req.assessmentId())
                .totalScore(result.totalScore())
                .maxScore(result.maxScore())
                .items(questionResults)
                .overallFeedback(result.overallFeedback())
                .assessedAt(result.assessedAt())
                .build();
    }
}
