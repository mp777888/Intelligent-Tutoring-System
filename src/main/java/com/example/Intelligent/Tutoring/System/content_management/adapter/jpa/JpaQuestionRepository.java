package com.example.Intelligent.Tutoring.System.content_management.adapter.jpa;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
