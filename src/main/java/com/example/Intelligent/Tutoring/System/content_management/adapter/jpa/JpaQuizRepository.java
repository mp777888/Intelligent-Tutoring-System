package com.example.Intelligent.Tutoring.System.content_management.adapter.jpa;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuizRepository extends JpaRepository<QuizEntity, Long> {
}
