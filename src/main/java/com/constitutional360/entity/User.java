package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "LONGTEXT")
    private String avatar;

    @Builder.Default
    private Integer streak = 0;

    @Builder.Default
    private Integer points = 0;

    @Builder.Default
    private Integer quizzesCompleted = 0;

    @Builder.Default
    private Integer articlesRead = 0;

    @Builder.Default
    private Integer timeSpent = 0;

    private LocalDate joinedDate;

    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;

    @PrePersist
    protected void onCreate() {
        if (joinedDate == null) joinedDate = LocalDate.now();
    }
}
