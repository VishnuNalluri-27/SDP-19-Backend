package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Optional user if logged in
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String query;

    @Column(nullable = false)
    private String category;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
