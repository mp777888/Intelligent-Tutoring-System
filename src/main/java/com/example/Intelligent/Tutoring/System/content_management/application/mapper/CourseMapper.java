package com.example.Intelligent.Tutoring.System.content_management.application.mapper;

import com.example.Intelligent.Tutoring.System.content_management.adapter.persistence.model.CourseEntity;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.CourseResponse;
import com.example.Intelligent.Tutoring.System.content_management.domain.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper {
    private QuizMapper quizMapper;

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getSubject()
        );
    }

    public CourseEntity toEntity(Course course) {
        return new CourseEntity(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getSubject(),
                course.getQuizzes() == null ? java.util.Collections.emptyList() :
                        course.getQuizzes().stream()
                                .map(quizMapper::toEntity)
                                .toList()
        );
    }

    public Course toDomain(CourseEntity courseEntity) {
        return new Course(
                courseEntity.getId(),
                courseEntity.getTitle(),
                courseEntity.getDescription(),
                courseEntity.getSubject(),
                courseEntity.getQuizzes() == null ? java.util.Collections.emptyList() :
                    courseEntity.getQuizzes().stream()
                            .map(quizMapper::toDomain)
                            .toList()
        );
    }
}
