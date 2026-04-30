package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuizSubmitDTO {
    private Long quizId;
    private Integer score;
    private Integer totalQuestions;
}
