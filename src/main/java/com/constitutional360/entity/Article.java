package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String part;

    @Column(nullable = false)
    private String title;

    @Column(name = "article_number")
    private String articleNumber;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "TEXT")
    private String simplified;

    private String category;

    private String importance;
}
