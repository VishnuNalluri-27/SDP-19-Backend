package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LeaderboardDTO {
    private Integer rank;
    private String name;
    private Integer points;
    private Integer streak;
    private Integer quizzes;
    private String badge;
}
