package com.constitutional360.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}
