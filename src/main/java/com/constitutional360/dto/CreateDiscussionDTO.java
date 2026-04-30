package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateDiscussionDTO {
    private String title;
    private String content;
    private String category;
}
