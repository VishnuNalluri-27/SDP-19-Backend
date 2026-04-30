package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flashcards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String front;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String back;
}
