package com.example.intelligenttutoringsystem.content.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.QuestionJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionRepositoryAdapter implements IQuestionRepository {

    private final QuestionJpaRepository jpaRepo;

    @Override
    public List<Question> list(String topicId, Integer difficulty) {
        return jpaRepo.findByFilter(topicId, difficulty);
    }

    @Override
    public Question get(String id) {
        return jpaRepo.findById(id).orElse(null);
    }

    @Override
    public Question save(Question q) {
        return jpaRepo.save(q);
    }
}
