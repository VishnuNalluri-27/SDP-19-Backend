package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonIgnore
    private Quiz quiz;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    // Stored as JSON string: ["option1","option2","option3","option4"]
    @Column(columnDefinition = "TEXT", nullable = false)
    private String options;

    @Column(nullable = false)
    private Integer correctIndex;

    @Column(columnDefinition = "TEXT")
    private String explanation;
}
