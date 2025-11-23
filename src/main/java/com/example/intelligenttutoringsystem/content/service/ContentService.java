package com.example.intelligenttutoringsystem.content.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.content.domain.Course;
import com.example.intelligenttutoringsystem.content.domain.Question;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.CourseJpaRepository;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.QuestionJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentService implements IContentService {

    private final CourseJpaRepository courseRepo;
    private final QuestionJpaRepository questionRepo;

    @Override
    @Transactional
    public Course create_course(Course course) {
        course.setCreatedAt(Instant.now());
        course.setUpdatedAt(Instant.now());
        course.setVersion(1);
        course.setValidFrom(Instant.now());
        course.setValidTo(null);
        return courseRepo.save(course);
    }

    @Override
    @Transactional
    public void add_question(Question question) {
        question.setCreatedAt(Instant.now());
        question.setUpdatedAt(Instant.now());
        question.setVersion(1);
        question.setValidFrom(Instant.now());
        question.setValidTo(null);
        questionRepo.save(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> get_questions(String topicId, Integer difficulty) {
        return questionRepo.findByFilter(topicId, difficulty);
    }
}
