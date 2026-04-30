package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vidhi_mitra_queries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VidhiMitraQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String queryText;

    @Column(columnDefinition = "TEXT")
    private String responseText;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
