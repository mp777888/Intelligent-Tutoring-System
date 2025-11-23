package com.example.intelligenttutoringsystem.content.application.dto;

import java.time.Instant;
import java.util.List;

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
public class CourseResponse {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer version;
    private Instant validFrom;
    private Instant validTo;
    private List<TopicResponse> topics;
}
