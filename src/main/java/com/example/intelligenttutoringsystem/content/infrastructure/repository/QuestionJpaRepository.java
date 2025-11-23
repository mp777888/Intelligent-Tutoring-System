package com.example.intelligenttutoringsystem.content.infrastructure.repository;

import com.example.intelligenttutoringsystem.content.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionJpaRepository extends JpaRepository<Question, String> {

    @Query("""
            select q from Question q
            join q.topic t
            where (:topicId is null or t.id = :topicId)
              and (:difficulty is null or q.difficulty = :difficulty)
            """)
    List<Question> findByFilter(String topicId, Integer difficulty);
}
