package com.constitutional360.controller;

import com.constitutional360.entity.VidhiMitraQuery;
import com.constitutional360.entity.User;
import com.constitutional360.repository.VidhiMitraQueryRepository;
import com.constitutional360.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vidhi-mitra")
@RequiredArgsConstructor
public class VidhiMitraQueryController {

    private final VidhiMitraQueryRepository vidhiMitraQueryRepository;
    private final UserRepository userRepository;

    @PostMapping("/log")
    public ResponseEntity<?> logQuery(@RequestBody QueryRequest request, Authentication authentication) {
        if (request.getQueryText() == null || request.getQueryText().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Query cannot be empty"));
        }

        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        }

        User user = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", "User not found"));
        }

        VidhiMitraQuery log = VidhiMitraQuery.builder()
                .queryText(request.getQueryText().trim())
                .responseText(request.getResponseText())
                .user(user)
                .build();

        vidhiMitraQueryRepository.save(log);

        return ResponseEntity.ok(Map.of("message", "Query logged successfully"));
    }

    @Data
    public static class QueryRequest {
        private String queryText;
        private String responseText;
    }
}
