package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discussions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;

    @Builder.Default
    private Integer likes = 0;

    @Builder.Default
    private Integer replyCount = 0;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
