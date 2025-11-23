package com.example.intelligenttutoringsystem.content.service;

import java.util.List;

import com.example.intelligenttutoringsystem.content.domain.Question;

public interface IQuestionRepository {
    List<Question> list(String topicId, Integer difficulty);

    Question get(String id);

    Question save(Question q);
}
