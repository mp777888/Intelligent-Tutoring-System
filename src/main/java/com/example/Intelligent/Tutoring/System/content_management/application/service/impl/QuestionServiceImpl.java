package com.example.Intelligent.Tutoring.System.content_management.application.service.impl;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.QuestionRepositoryAdapter;
import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.QuizRepositoryAdapter;
import com.example.Intelligent.Tutoring.System.content_management.application.QuestionType;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.IQuestionUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionServiceImpl implements IQuestionUseCase {
    final QuestionRepositoryAdapter questionRepository;
    final QuizRepositoryAdapter quizRepository;
    @Override
    public Question addQuestion(AddQuestionCommand command) {
        log.info("Adding new question with content: {}", command.content());

        var quiz =  quizRepository.getById(command.quizId());
        if(quiz == null) {
            throw new IllegalArgumentException("Quiz not found with id: " + command.quizId());
        }

        if(QuestionType.valueOf(command.type()) == null) {
            throw new IllegalArgumentException("Invalid question type: " + command.type());
        }


        var question = Question.builder()
                .content(command.content())
                .options(command.options())
                .correctAnswer(command.correctAnswer())
                .quiz(quiz)
                .type(QuestionType.valueOf(command.type()))
                .build();
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions(GetQuestionsByQuizIdCommand command) {
        log.info("Fetching all questions");
        // Implement filtering logic based on the filter criteria
        return questionRepository.findAllByQuizId(command.quizId());
    }

    @Override
    public Iterable<Question> getQuestions(QuestionFilter filter) {
        return null;
    }

}
