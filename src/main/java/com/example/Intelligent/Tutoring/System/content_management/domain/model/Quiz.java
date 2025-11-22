package com.example.Intelligent.Tutoring.System.content_management.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz {
    Long id;
    String title;
    String description;
    Course course;
    @Builder.Default
    List<Question> questions = new ArrayList<>();
    LocalDateTime createdAt;
    LocalDateTime closedAt;
}
