package com.example.Intelligent.Tutoring.System.content_management.domain.port.in;

import com.example.Intelligent.Tutoring.System.content_management.application.QuestionType;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;

import java.util.List;

public interface IQuestionUseCase {
    Question addQuestion(AddQuestionCommand command);
    List<Question> getAllQuestions(GetQuestionsByQuizIdCommand command);
    Iterable<Question> getQuestions(QuestionFilter filter);
    record AddQuestionCommand(Long quizId, String content,String type, List<String> options, String correctAnswer) {}
    record GetQuestionsByQuizIdCommand(Long quizId) {}
    record QuestionFilter(Long courseId, Long lessonId, QuestionType type) {}
}
