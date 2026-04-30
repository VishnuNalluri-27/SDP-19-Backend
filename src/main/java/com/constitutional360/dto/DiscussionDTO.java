package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DiscussionDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String role;
    private String category;
    private String date;
    private Integer replies;
    private Integer likes;
    private String preview;
}
