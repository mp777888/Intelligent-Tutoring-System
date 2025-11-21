package com.example.Intelligent.Tutoring.System.content_management.adapter.rest;

import com.example.Intelligent.Tutoring.System.content_management.application.dto.request.CourseCreateRequest;
import com.example.Intelligent.Tutoring.System.content_management.application.dto.response.CourseResponse;
import com.example.Intelligent.Tutoring.System.content_management.application.mapper.CourseMapper;
import com.example.Intelligent.Tutoring.System.content_management.domain.port.in.CreateCourseUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ContentManagementController {
    final CreateCourseUseCase createCourseUseCase;
    final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreateRequest request) {
        var command = new CreateCourseUseCase.CreateCourseCommand(
                request.title(),
                request.description(),
                request.subject()
        );

        var course = createCourseUseCase.createCourse(command);
        return ResponseEntity.ok(courseMapper.toResponse(course));
    }

    @GetMapping
    public ResponseEntity<CourseResponse> getCourseById(@RequestBody Long id) {
        var course = createCourseUseCase.findCourseById(id);
        return ResponseEntity.ok(courseMapper.toResponse(course));
    }
}
