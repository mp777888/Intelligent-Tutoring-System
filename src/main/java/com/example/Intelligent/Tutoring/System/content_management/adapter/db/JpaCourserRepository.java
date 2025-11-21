package com.example.Intelligent.Tutoring.System.content_management.adapter.db;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourserRepository extends JpaRepository<CourseEntity, Long> {
}
