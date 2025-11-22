package com.example.Intelligent.Tutoring.System.content_management.adapter.persistence;


import com.example.Intelligent.Tutoring.System.content_management.adapter.jpa.JpaCourserRepository;
import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.CourseEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.CourseMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.CourseRepositoryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseRepositoryAdapter implements CourseRepositoryPort {
    JpaCourserRepository jpaCourseRepository;
    CourseMapper courseMapper;

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = courseMapper.toEntity(course);
        CourseEntity savedEntity = jpaCourseRepository.save(courseEntity);
        return courseMapper.toDomain(savedEntity);
    }

    @Override
    public Course findById(Long id) {
        return jpaCourseRepository.findById(id)
                .map(courseMapper::toDomain)
                .orElse(null);
    }
}
