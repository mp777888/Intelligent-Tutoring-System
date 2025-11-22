package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Question;

public interface QuestionRepositoryPort {
    Question save(Question question);
}
