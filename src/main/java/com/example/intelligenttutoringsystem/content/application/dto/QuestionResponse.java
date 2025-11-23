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
public class QuestionResponse {
    private String id;
    private String content;
    private Integer difficulty;
    private String questionType;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer version;
    private Instant validFrom;
    private Instant validTo;
    private List<ChoiceResponse> choices;
}
