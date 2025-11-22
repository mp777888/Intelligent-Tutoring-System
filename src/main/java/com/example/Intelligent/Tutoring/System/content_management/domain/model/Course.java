package com.example.Intelligent.Tutoring.System.content_management.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    Long id;
    String title;
    String description;
    String subject;
    @Builder.Default
    List<Quiz> quizzes = new ArrayList<>();;
}
