package com.example.Intelligent.Tutoring.System.content_management.application.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse {
    Long id;
    Long courseId;
    String title;
    String description;
    List<QuestionResponse> questions;
    LocalDateTime createdAt;
    LocalDateTime closedAt;
}
