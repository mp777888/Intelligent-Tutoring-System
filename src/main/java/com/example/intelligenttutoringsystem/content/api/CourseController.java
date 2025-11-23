package com.example.intelligenttutoringsystem.content.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.intelligenttutoringsystem.content.application.CourseService;
import com.example.intelligenttutoringsystem.content.application.CreateCourseRequest;
import com.example.intelligenttutoringsystem.content.application.UpdateCourseRequest;
import com.example.intelligenttutoringsystem.content.application.dto.ContentMapper;
import com.example.intelligenttutoringsystem.content.application.dto.CourseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final ContentMapper contentMapper;

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CreateCourseRequest req) {
        var created = courseService.createCourse(req);
        CourseResponse response = contentMapper.toCourseResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public CourseResponse get(@PathVariable String id) {
        var course = courseService.getCourse(id);
        return contentMapper.toCourseResponse(course);
    }

    @GetMapping
    public List<CourseResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var courses = courseService.listCourses(page, size);
        return courses.stream().map(contentMapper::toCourseResponse).toList();
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable String id, @RequestBody UpdateCourseRequest req) {
        var updated = courseService.updateCourse(id, req);
        return contentMapper.toCourseResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
