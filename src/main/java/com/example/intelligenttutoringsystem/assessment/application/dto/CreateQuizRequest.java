package com.example.intelligenttutoringsystem.assessment.application.dto;

import java.util.List;

public record CreateQuizRequest(String title, String description, String topicId, List<String> questionIds) {
}
