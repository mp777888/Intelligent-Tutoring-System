package com.example.Intelligent.Tutoring.System.content_management.adapter.persistence;


import com.example.Intelligent.Tutoring.System.content_management.adapter.db.JpaCourserRepository;
import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.CourseEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.CourseMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.out.CourseRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryAdapter implements CourseRepositoryPort {
    final JpaCourserRepository jpaCourseRepository;
    final CourseMapper courseMapper;

    public CourseRepositoryAdapter(JpaCourserRepository jpaCourseRepository, CourseMapper courseMapper) {
        this.jpaCourseRepository = jpaCourseRepository;
        this.courseMapper = courseMapper;
    }

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
