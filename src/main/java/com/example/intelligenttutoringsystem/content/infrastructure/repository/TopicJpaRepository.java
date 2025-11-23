package com.example.intelligenttutoringsystem.content.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.content.domain.Topic;

public interface TopicJpaRepository extends JpaRepository<Topic, String> {
    List<Topic> findByCourseId(String courseId);
}
