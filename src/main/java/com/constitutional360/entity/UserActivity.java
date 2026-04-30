package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Types: ARTICLE_READ, QUIZ_COMPLETED, DISCUSSION_POSTED, LOGIN
    @Column(nullable = false)
    private String activityType;

    private Long referenceId;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
