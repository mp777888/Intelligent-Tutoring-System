package com.example.intelligenttutoringsystem.content.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "content_choices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean isCorrect;
}
