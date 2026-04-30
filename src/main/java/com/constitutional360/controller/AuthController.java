package com.constitutional360.controller;

import com.constitutional360.dto.AuthRequest;
import com.constitutional360.dto.AuthResponse;
import com.constitutional360.dto.SignupRequest;
import com.constitutional360.dto.UserDTO;
import com.constitutional360.entity.RefreshToken;
import com.constitutional360.security.JwtTokenProvider;
import com.constitutional360.service.AuthService;
import com.constitutional360.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider tokenProvider;

    @Value("${app.jwt.refreshExpirationMs:604800000}")
    private Long refreshTokenDurationMs;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        try {
            AuthResponse authResponse = authService.login(request);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authResponse.getId());
            addRefreshTokenCookie(response, refreshToken.getToken());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "Login error: " + e.getMessage() + " / " + e.getClass().getName()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest request, HttpServletResponse response) {
        try {
            AuthResponse authResponse = authService.register(request);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authResponse.getId());
            addRefreshTokenCookie(response, refreshToken.getToken());
            return ResponseEntity.ok(authResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshTokenStr = getCookieValueByName(request, "refreshToken");
        if (refreshTokenStr != null) {
            Optional<RefreshToken> tokenOpt = refreshTokenService.findByToken(refreshTokenStr);
            if (tokenOpt.isPresent()) {
                RefreshToken token = tokenOpt.get();
                try {
                    refreshTokenService.verifyExpiration(token);
                    String newAccessToken = tokenProvider.generateTokenFromEmail(token.getUser().getEmail());
                    return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
                } catch (RuntimeException e) {
                    return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
                }
            }
        }
        return ResponseEntity.status(401).body(Map.of("message", "Refresh token is invalid or missing!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication, HttpServletResponse response) {
        if (authentication != null && authentication.getName() != null) {
            try {
                UserDTO user = authService.getCurrentUser(authentication.getName());
                refreshTokenService.deleteByUserId(user.getId());
            } catch (Exception ignored) {}
        }
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false) // set true in production with HTTPS
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        try {
            UserDTO user = authService.getCurrentUser(authentication.getName());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "User not found"));
        }
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false) // Set to true in production if HTTPS is used
                .path("/")
                .maxAge(refreshTokenDurationMs / 1000)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
