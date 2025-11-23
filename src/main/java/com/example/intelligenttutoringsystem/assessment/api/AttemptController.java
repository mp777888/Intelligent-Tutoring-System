package com.example.intelligenttutoringsystem.assessment.api;

import com.example.intelligenttutoringsystem.assessment.api.dto.AttemptDto;
import com.example.intelligenttutoringsystem.assessment.application.AttemptService;
import com.example.intelligenttutoringsystem.assessment.domain.Attempt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments/attempts")
@RequiredArgsConstructor
@Tag(name = "Attempt Controller", description = "API for managing assessment attempts")
public class AttemptController {

    private final AttemptService attemptService;

    @GetMapping("/{id}")
    @Operation(summary = "Get attempt by ID")
    @ApiResponse(responseCode = "200", description = "Attempt found", content = @Content(schema = @Schema(implementation = AttemptDto.class)))
    @ApiResponse(responseCode = "404", description = "Attempt not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public AttemptDto get(@PathVariable @NonNull String id) {
        try {
            Attempt attempt = attemptService.getAttempt(id);
            return AttemptDto.from(attempt);
        } catch (IllegalArgumentException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve attempt", e);
        }
    }

    @GetMapping
    @Operation(summary = "List attempts with optional filters")
    @ApiResponse(responseCode = "200", description = "List of attempts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttemptDto.class))))
    public List<AttemptDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String assessmentId) {
        
        List<Attempt> attempts;
        if (studentId != null && assessmentId != null) {
            attempts = attemptService.listByStudentAndAssessment(studentId, assessmentId);
        } else if (studentId != null) {
            attempts = attemptService.listByStudent(studentId);
        } else if (assessmentId != null) {
            attempts = attemptService.listByAssessment(assessmentId);
        } else {
            attempts = attemptService.listAttempts(page, size);
        }
        
        return attempts.stream()
                .map(AttemptDto::from)
                .toList();
    }
}
