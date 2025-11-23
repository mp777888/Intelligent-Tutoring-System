package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.CreateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.UpdateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Quiz;
import com.example.intelligenttutoringsystem.assessment.domain.QuizQuestion;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.QuizJpaRepository;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.QuizQuestionJpaRepository;
import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.domain.Topic;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.QuestionJpaRepository;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.TopicJpaRepository;

@Service
public class QuizService {

    private final QuizJpaRepository quizRepo;
    private final TopicJpaRepository topicRepo;
    private final QuizQuestionJpaRepository quizQuestionRepo;
    private final QuestionJpaRepository questionRepo;

    public QuizService(QuizJpaRepository quizRepo, TopicJpaRepository topicRepo,
            QuizQuestionJpaRepository quizQuestionRepo, QuestionJpaRepository questionRepo) {
        this.quizRepo = quizRepo;
        this.topicRepo = topicRepo;
        this.quizQuestionRepo = quizQuestionRepo;
        this.questionRepo = questionRepo;
    }

    @Transactional
    public Quiz createQuiz(CreateQuizRequest dto) {
        if (dto.topicId() == null) {
            throw new IllegalArgumentException("Topic ID cannot be null");
        }

        Topic topic = topicRepo.findById(dto.topicId())
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

        Quiz q = Quiz.builder()
                .title(dto.title())
                .description(dto.description())
                .topic(topic)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Quiz savedQuiz = quizRepo.save(q);

        // Add questions to quiz if provided
        if (dto.questionIds() != null && !dto.questionIds().isEmpty()) {
            List<QuizQuestion> quizQuestions = dto.questionIds().stream()
                    .map(questionId -> {
                        Question question = questionRepo.findById(questionId)
                                .orElseThrow(() -> new IllegalArgumentException("Question not found: " + questionId));

                        // Validate that question belongs to the topic
                        if (!question.getTopic().getId().equals(dto.topicId())) {
                            throw new IllegalArgumentException("Question does not belong to the specified topic");
                        }

                        return QuizQuestion.builder()
                                .quiz(savedQuiz)
                                .question(question)
                                .build();
                    })
                    .toList();

            quizQuestionRepo.saveAll(quizQuestions);
            savedQuiz.setQuizQuestions(quizQuestions);
        }

        return savedQuiz;
    }

    @Transactional(readOnly = true)
    public Quiz getQuiz(String id) {
        return quizRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
    }

    @Transactional(readOnly = true)
    public List<Quiz> listQuizzes(int page, int size) {
        return quizRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional(readOnly = true)
    public List<Quiz> getQuizzesByTopic(String topicId) {
        return quizRepo.findByTopicId(topicId);
    }

    @Transactional
    public Quiz updateQuiz(String id, UpdateQuizRequest dto) {
        Quiz q = getQuiz(id);
        if (dto.title() != null)
            q.setTitle(dto.title());
        if (dto.description() != null)
            q.setDescription(dto.description());

        if (dto.topicId() != null && !dto.topicId().equals(q.getTopic().getId())) {
            Topic newTopic = topicRepo.findById(dto.topicId())
                    .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
            q.setTopic(newTopic);
        }

        q.setUpdatedAt(Instant.now());
        return quizRepo.save(q);
    }

    @Transactional
    public void deleteQuiz(String id) {
        if (!quizRepo.existsById(id))
            throw new IllegalArgumentException("Quiz not found");
        quizRepo.deleteById(id);
    }
}
