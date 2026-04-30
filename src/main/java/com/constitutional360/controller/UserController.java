package com.constitutional360.controller;

import com.constitutional360.dto.ProfileUpdateDTO;
import com.constitutional360.dto.UserDTO;
import com.constitutional360.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(Authentication auth, @RequestBody ProfileUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateProfile(auth.getName(), dto));
    }
}
