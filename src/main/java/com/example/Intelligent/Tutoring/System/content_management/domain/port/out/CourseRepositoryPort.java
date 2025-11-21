package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;

public interface CourseRepositoryPort {
    Course save(Course course);
    Course findById(Long id);
}
