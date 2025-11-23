package com.example.intelligenttutoringsystem.assessment.application.dto;

import java.time.Instant;
import java.util.List;

import com.example.intelligenttutoringsystem.content.application.dto.QuestionResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    private String id;
    private String title;
    private String description;
    private String topicId;
    private String topicName;
    private Instant createdAt;
    private Instant updatedAt;
    private List<QuestionResponse> questions;
}
