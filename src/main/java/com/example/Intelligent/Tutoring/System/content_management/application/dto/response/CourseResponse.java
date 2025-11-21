package com.example.Intelligent.Tutoring.System.content_management.application.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    Long id;
    String title;
    String description;
    String subject;
}
