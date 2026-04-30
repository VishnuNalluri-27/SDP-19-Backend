package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_results")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private Integer score;
    private Integer totalQuestions;

    @Builder.Default
    private LocalDateTime attemptedAt = LocalDateTime.now();
}
