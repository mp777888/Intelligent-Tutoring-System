package com.example.Intelligent.Tutoring.System.content_management.application.service.impl;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.QuestionRepositoryAdapter;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.IQuestionUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionServiceIml implements IQuestionUseCase {
    final QuestionRepositoryAdapter questionRepository;

    @Override
    public Question addQuestion(AddQuestionCommand command) {
        log.info("Adding new question with content: {}", command.content());

        var question = Question.builder()
                .content(command.content())
                .options(command.options())
                .correctAnswer(command.correctAnswer())
                .build();
        return questionRepository.save(question);
    }

    @Override
    public Iterable<Question> getQuestions(QuestionFilter filter) {
        return null;
    }

}
