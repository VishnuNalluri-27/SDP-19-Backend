package com.constitutional360.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private List<QuestionDTO> questions;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class QuestionDTO {
        private Long id;
        private String question;
        private List<String> options;
        private Integer correct;
        private String explanation;
    }
}
