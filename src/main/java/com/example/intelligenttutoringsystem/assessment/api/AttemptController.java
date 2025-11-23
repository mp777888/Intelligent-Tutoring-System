package com.example.intelligenttutoringsystem.assessment.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.intelligenttutoringsystem.assessment.application.AttemptService;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;

    @GetMapping("/{id}")
    public Attempt get(@PathVariable String id) {
        return attemptService.getAttempt(id);
    }

    @GetMapping
    public List<Attempt> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return attemptService.listAttempts(page, size);
    }

    @GetMapping(params = "studentId")
    public List<Attempt> listByStudent(@RequestParam String studentId) {
        return attemptService.listByStudent(studentId);
    }

    @GetMapping(params = "assessmentId")
    public List<Attempt> listByAssessment(@RequestParam String assessmentId) {
        return attemptService.listByAssessment(assessmentId);
    }
}
