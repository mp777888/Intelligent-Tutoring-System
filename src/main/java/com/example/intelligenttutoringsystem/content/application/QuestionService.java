package com.example.intelligenttutoringsystem.content.application;

import com.example.intelligenttutoringsystem.content.domain.Choice;
import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.domain.Topic;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.QuestionJpaRepository;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.TopicJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

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
    public List<Question> getQuestionsForAssessment(String topicId, Integer difficulty, int limit) {
        List<Question> all = questionRepo.findByFilter(topicId, difficulty);
        if (all.size() > limit) {
            return all.subList(0, limit);
        }
        return all;
    }
}
