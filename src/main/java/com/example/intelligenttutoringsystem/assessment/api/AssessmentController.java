package com.example.intelligenttutoringsystem.assessment.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.intelligenttutoringsystem.assessment.application.AssessmentService;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.StartAssessmentResponse;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.SubmitAssessmentResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/start")
    public StartAssessmentResponse start(@RequestBody StartAssessmentRequest req) {
        return assessmentService.startAssessment(req);
    }

    @PostMapping("/submit")
    public SubmitAssessmentResponse submit(@RequestBody SubmitAssessmentRequest req) {
        return assessmentService.submitAssessment(req);
    }
}
