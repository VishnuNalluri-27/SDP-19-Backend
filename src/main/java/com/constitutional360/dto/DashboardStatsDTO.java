package com.constitutional360.dto;

import lombok.*;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DashboardStatsDTO {
    private Integer articlesRead;
    private Integer quizzesCompleted;
    private Integer points;
    private Integer streak;
    private Integer timeSpent;
    private String dailyFact;
}
