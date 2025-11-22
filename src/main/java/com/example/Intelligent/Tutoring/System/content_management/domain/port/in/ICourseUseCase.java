package com.example.Intelligent.Tutoring.System.content_management.domain.port.in;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;

public interface ICourseUseCase {
    Course createCourse(CreateCourseCommand command);
    Course findCourseById(Long id);
    record CreateCourseCommand(String title, String description, String subject) {}
}
