package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rights")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String situation;

    // JSON array of rights strings
    @Column(columnDefinition = "TEXT")
    private String rightsJson;

    @Column(columnDefinition = "TEXT")
    private String guidance;

    @Column(columnDefinition = "TEXT")
    private String outcome;
}
