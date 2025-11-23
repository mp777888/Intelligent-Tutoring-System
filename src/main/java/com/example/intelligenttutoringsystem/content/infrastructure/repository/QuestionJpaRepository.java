package com.example.intelligenttutoringsystem.content.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.intelligenttutoringsystem.content.domain.Question;

public interface QuestionJpaRepository extends JpaRepository<Question, String> {

  @Query("""
      select q from Question q
      join q.topic t
      where (:topicId is null or t.id = :topicId)
        and (:difficulty is null or q.difficulty = :difficulty)
      """)
  List<Question> findByFilter(String topicId, Integer difficulty);
}
