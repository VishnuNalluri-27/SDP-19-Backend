package com.constitutional360.controller;

import com.constitutional360.entity.SearchLog;
import com.constitutional360.entity.User;
import com.constitutional360.repository.SearchLogRepository;
import com.constitutional360.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchLogController {

    private final SearchLogRepository searchLogRepository;
    private final UserRepository userRepository;

    @PostMapping("/log")
    public ResponseEntity<?> logSearch(@RequestBody SearchRequest request, Authentication authentication) {
        if (request.getQuery() == null || request.getQuery().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Query cannot be empty"));
        }

        User user = null;
        if (authentication != null && authentication.getName() != null) {
            user = userRepository.findByEmail(authentication.getName()).orElse(null);
        }

        SearchLog log = SearchLog.builder()
                .query(request.getQuery().trim())
                .category(request.getCategory() == null || request.getCategory().isEmpty() ? "All" : request.getCategory())
                .user(user)
                .build();

        searchLogRepository.save(log);

        return ResponseEntity.ok(Map.of("message", "Search logged successfully"));
    }

    @Data
    public static class SearchRequest {
        private String query;
        private String category;
    }
}
