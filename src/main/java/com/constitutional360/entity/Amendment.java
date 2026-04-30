package com.constitutional360.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "amendments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Amendment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    @Column(name = "amendment_number")
    private String number;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
}
