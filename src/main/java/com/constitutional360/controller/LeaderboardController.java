package com.constitutional360.controller;

import com.constitutional360.dto.LeaderboardDTO;
import com.constitutional360.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<LeaderboardDTO>> getLeaderboard() {
        return ResponseEntity.ok(leaderboardService.getLeaderboard());
    }
}
