package com.constitutional360.service;

import com.constitutional360.dto.*;
import com.constitutional360.entity.Role;
import com.constitutional360.entity.User;
import com.constitutional360.repository.UserRepository;
import com.constitutional360.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = tokenProvider.generateToken(authentication);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return buildAuthResponse(token, user);
    }

    public AuthResponse register(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        Role role = switch (request.getRole().toLowerCase()) {
            case "admin" -> Role.ROLE_ADMIN;
            case "educator" -> Role.ROLE_EDUCATOR;
            case "legal" -> Role.ROLE_LEGAL;
            default -> Role.ROLE_CITIZEN;
        };

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        user = userRepository.save(user);
        String token = tokenProvider.generateTokenFromEmail(user.getEmail());
        return buildAuthResponse(token, user);
    }

    public UserDTO getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return buildUserDTO(user);
    }

    private AuthResponse buildAuthResponse(String token, User user) {
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name().replace("ROLE_", "").toLowerCase())
                .avatar(user.getAvatar())
                .streak(user.getStreak())
                .points(user.getPoints())
                .quizzesCompleted(user.getQuizzesCompleted())
                .articlesRead(user.getArticlesRead())
                .joinedDate(user.getJoinedDate() != null ? user.getJoinedDate().toString() : null)
                .profilePic(user.getProfilePic())
                .timeSpent(user.getTimeSpent())
                .build();
    }

    private UserDTO buildUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name().replace("ROLE_", "").toLowerCase())
                .avatar(user.getAvatar())
                .streak(user.getStreak())
                .points(user.getPoints())
                .quizzesCompleted(user.getQuizzesCompleted())
                .articlesRead(user.getArticlesRead())
                .joinedDate(user.getJoinedDate() != null ? user.getJoinedDate().toString() : null)
                .profilePic(user.getProfilePic())
                .timeSpent(user.getTimeSpent())
                .build();
    }
}
