package com.constitutional360.service;

import com.constitutional360.dto.LeaderboardDTO;
import com.constitutional360.entity.User;
import com.constitutional360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final UserRepository userRepository;

    public List<LeaderboardDTO> getLeaderboard() {
        List<User> users = userRepository.findAllByOrderByPointsDesc();
        AtomicInteger rank = new AtomicInteger(1);

        return users.stream()
                .map(user -> {
                    int r = rank.getAndIncrement();
                    String badge = switch (r) {
                        case 1 -> "🏆";
                        case 2 -> "🥈";
                        case 3 -> "🥉";
                        default -> r <= 5 ? "⭐" : r <= 8 ? "🔥" : "🌟";
                    };
                    return LeaderboardDTO.builder()
                            .rank(r)
                            .name(user.getName())
                            .points(user.getPoints())
                            .streak(user.getStreak())
                            .quizzes(user.getQuizzesCompleted())
                            .badge(badge)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
