package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.CreateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.UpdateQuizRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Quiz;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.QuizJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizJpaRepository quizRepo;

    @Transactional
    public Quiz createQuiz(CreateQuizRequest dto) {
        Quiz q = Quiz.builder()
                .title(dto.title())
                .description(dto.description())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return quizRepo.save(q);
    }

    @Transactional(readOnly = true)
    public Quiz getQuiz(String id) {
        return quizRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
    }

    @Transactional(readOnly = true)
    public List<Quiz> listQuizzes(int page, int size) {
        return quizRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional
    public Quiz updateQuiz(String id, UpdateQuizRequest dto) {
        Quiz q = getQuiz(id);
        if (dto.title() != null)
            q.setTitle(dto.title());
        if (dto.description() != null)
            q.setDescription(dto.description());
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
