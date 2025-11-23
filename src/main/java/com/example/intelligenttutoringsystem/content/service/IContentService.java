package com.example.intelligenttutoringsystem.content.service;

import java.util.List;

import com.example.intelligenttutoringsystem.content.domain.Course;
import com.example.intelligenttutoringsystem.content.domain.Question;

public interface IContentService {
    Course create_course(Course course);

    void add_question(Question question);

    List<Question> get_questions(String topicId, Integer difficulty);
}
