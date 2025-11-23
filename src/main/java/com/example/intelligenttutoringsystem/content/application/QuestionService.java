package com.example.intelligenttutoringsystem.content.application;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.content.domain.Choice;
import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.domain.Topic;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.QuestionJpaRepository;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.TopicJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionJpaRepository questionRepo;
    private final TopicJpaRepository topicRepo;

    @Transactional
    public Question createQuestion(CreateQuestionRequest dto) {
        Topic topic = topicRepo.findById(dto.topicId())
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

        Question question = Question.builder()
                .topic(topic)
                .content(dto.content())
                .difficulty(dto.difficulty())
                .questionType("MCQ")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        List<Choice> choices = dto.choices().stream()
                .map(c -> Choice.builder()
                        .question(question)
                        .content(c.content())
                        .isCorrect(c.isCorrect())
                        .build())
                .toList();

        question.setChoices(choices);

        return questionRepo.save(question);
    }

    @Transactional(readOnly = true)
    public Question getQuestion(String id) {
        return questionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    }

    @Transactional
    public Question updateQuestion(String id, UpdateQuestionRequest dto) {
        Question question = getQuestion(id);
        if (dto.topicId() != null && !Objects.equals(dto.topicId(), question.getTopic().getId())) {
            Topic topic = topicRepo.findById(dto.topicId())
                    .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
            question.setTopic(topic);
        }
        if (dto.content() != null)
            question.setContent(dto.content());
        if (dto.difficulty() != null)
            question.setDifficulty(dto.difficulty());
        if (dto.choices() != null && !dto.choices().isEmpty()) {
            question.getChoices().clear();
            List<Choice> newChoices = dto.choices().stream()
                    .map(c -> Choice.builder()
                            .question(question)
                            .content(c.content())
                            .isCorrect(c.isCorrect())
                            .build())
                    .toList();
            question.getChoices().addAll(newChoices);
        }
        question.setUpdatedAt(Instant.now());
        return questionRepo.save(question);
    }

    @Transactional
    public void deleteQuestion(String id) {
        if (!questionRepo.existsById(id)) {
            throw new IllegalArgumentException("Question not found");
        }
        questionRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestionsForAssessment(String topicId, Integer difficulty, int limit) {
        List<Question> all = questionRepo.findByFilter(topicId, difficulty);
        if (all.size() > limit) {
            return all.subList(0, limit);
        }
        return all;
    }

    @Transactional(readOnly = true)
    public List<Question> listQuestions(int page, int size) {
        return questionRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }
}
