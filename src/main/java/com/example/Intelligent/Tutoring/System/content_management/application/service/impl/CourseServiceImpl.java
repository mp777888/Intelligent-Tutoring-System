package com.example.Intelligent.Tutoring.System.content_management.application.service.impl;

import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.ICourseUseCase;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.CourseRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseServiceImpl implements ICourseUseCase {

    final CourseRepositoryPort courseRepository;

    @Override
    public Course createCourse(CreateCourseCommand command) {
        log.info("Creating new course with title: {}", command.title());

        var course = Course.builder()
                .title(command.title())
                .description(command.description())
                .subject(command.subject())
                .build();

        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Long id) {
        log.info("Finding course by id: {}", id);
        try {
            var course = courseRepository.findById(id);
            if (course == null) {
                log.warn("Course not found with id: {}", id);
                throw new IllegalArgumentException("Course not found with id: " + id);
            }
            return course;
        } catch (Exception e) {
            log.error("Failed to find course with id: {}", id, e);
            throw new RuntimeException("Failed to find course with id: " + id, e);
        }
    }
}
