package com.example.intelligenttutoringsystem.content.infrastructure.repository;

import com.example.intelligenttutoringsystem.content.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicJpaRepository extends JpaRepository<Topic, String> {
}
