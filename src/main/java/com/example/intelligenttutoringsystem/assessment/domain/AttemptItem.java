package com.example.intelligenttutoringsystem.assessment.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assessment_attempt_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    private Attempt attempt;

    private String questionId;
    private String selectedChoiceId;

    private Boolean isCorrect;
    private Double score;

    @Column(columnDefinition = "TEXT")
    private String feedback;
}
