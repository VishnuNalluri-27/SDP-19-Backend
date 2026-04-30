package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String avatar;
    private Integer streak;
    private Integer points;
    private Integer quizzesCompleted;
    private Integer articlesRead;
    private String joinedDate;
    private String profilePic;
    private Integer timeSpent;
}
