package com.example.intelligenttutoringsystem.assessment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.intelligenttutoringsystem.assessment.application.AssessmentService;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/start")
    public Map<String, Object> start(@RequestBody StartAssessmentRequest req) {
        return assessmentService.startAssessment(req);
    }

    @PostMapping("/submit")
    public Map<String, Object> submit(@RequestBody SubmitAssessmentRequest req) {
        return assessmentService.submitAssessment(req);
    }
}
