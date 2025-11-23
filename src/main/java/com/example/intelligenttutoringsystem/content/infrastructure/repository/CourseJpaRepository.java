package com.example.intelligenttutoringsystem.content.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.content.domain.Course;

public interface CourseJpaRepository extends JpaRepository<Course, String> {
}
