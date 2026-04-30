package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfileUpdateDTO {
    private String name;
    private String email;
    private String profilePic;
}
