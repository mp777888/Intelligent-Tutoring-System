package com.example.Intelligent.Tutoring.System.content_management.adapter.jpa;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.QuizEntity;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaQuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findAllByCourseId(Long courseId);
}
