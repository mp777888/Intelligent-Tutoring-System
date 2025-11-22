package com.example.Intelligent.Tutoring.System.content_management.domain.port.in;

import com.example.Intelligent.Tutoring.System.content_management.application.QuestionType;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;

import java.util.List;

public interface IQuestionUseCase {
    Question addQuestion(AddQuestionCommand command);
    Iterable<Question> getQuestions(QuestionFilter filter);
    record AddQuestionCommand(Long quizId, String content, List<String> options, String correctAnswer, QuestionType type) {}
    record QuestionFilter(Long courseId, Long lessonId, QuestionType type) {}
}
