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
import com.example.intelligenttutoringsystem.content.domain.Course;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody CreateCourseRequest req) {
        Course created = courseService.createCourse(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @GetMapping
    public List<Course> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return courseService.listCourses(page, size);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable String id, @RequestBody UpdateCourseRequest req) {
        return courseService.updateCourse(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
