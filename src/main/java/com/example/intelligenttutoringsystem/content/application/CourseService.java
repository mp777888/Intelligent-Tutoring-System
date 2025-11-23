package com.example.intelligenttutoringsystem.content.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.content.domain.Course;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.CourseJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseJpaRepository courseRepo;

    @Transactional
    public Course createCourse(CreateCourseRequest dto) {
        Course c = Course.builder()
                .name(dto.name())
                .description(dto.description())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return courseRepo.save(c);
    }

    @Transactional(readOnly = true)
    public Course getCourse(String id) {
        return courseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    @Transactional(readOnly = true)
    public List<Course> listCourses(int page, int size) {
        return courseRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional
    public Course updateCourse(String id, UpdateCourseRequest dto) {
        Course c = getCourse(id);
        if (dto.name() != null)
            c.setName(dto.name());
        if (dto.description() != null)
            c.setDescription(dto.description());
        c.setUpdatedAt(Instant.now());
        return courseRepo.save(c);
    }

    @Transactional
    public void deleteCourse(String id) {
        if (!courseRepo.existsById(id))
            throw new IllegalArgumentException("Course not found");
        courseRepo.deleteById(id);
    }
}
