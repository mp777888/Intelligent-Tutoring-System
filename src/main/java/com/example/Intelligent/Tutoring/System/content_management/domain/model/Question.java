package com.example.Intelligent.Tutoring.System.content_management.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {
    Long id;
    String content;
    String correctAnswer;
    double difficultyWeight;
}
