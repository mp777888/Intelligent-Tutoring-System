package com.example.Intelligent.Tutoring.System.content_management.domain.model;

import com.example.Intelligent.Tutoring.System.content_management.application.QuestionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


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
    Quiz quiz;
    List<String> options;
    QuestionType type;
}
