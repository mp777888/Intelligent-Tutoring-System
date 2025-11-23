package com.example.intelligenttutoringsystem.assessment.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intelligenttutoringsystem.assessment.domain.StudentProfile;

public interface StudentProfileJpaRepository extends JpaRepository<StudentProfile, String> {
    Optional<StudentProfile> findByStudentId(String studentId);
}
