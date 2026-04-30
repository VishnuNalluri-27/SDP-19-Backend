package com.constitutional360.service;

import com.constitutional360.dto.DashboardStatsDTO;
import com.constitutional360.entity.User;
import com.constitutional360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;

    private static final String[] DAILY_FACTS = {
        "The Indian Constitution is the longest written constitution in the world with 448 articles in 25 parts.",
        "Dr. B.R. Ambedkar is known as the \"Father of the Indian Constitution.\"",
        "The Constitution was adopted on November 26, 1949, and came into effect on January 26, 1950.",
        "It took 2 years, 11 months and 18 days to complete the Indian Constitution.",
        "The original Constitution was handwritten and calligraphed by Prem Behari Narain Raizada.",
        "The Constitution originally had 395 articles and 8 schedules. Now it has 448 articles and 12 schedules.",
        "The Preamble was inspired by the American Constitution's preamble.",
        "The concept of Fundamental Duties was borrowed from the Constitution of the former Soviet Union.",
        "The idea of Directive Principles was inspired by the Irish Constitution.",
        "Republic Day (January 26) celebrates the date the Constitution came into effect.",
        "The word \"secular\" was added to the Preamble by the 42nd Amendment in 1976.",
        "Article 32 was called the \"heart and soul\" of the Constitution by Dr. Ambedkar.",
        "The Indian Constitution provides for both a federal and unitary system of government.",
        "The Emergency provisions (Article 352-360) allow the government to override fundamental rights in extreme situations.",
        "The Constitution provides for an independent judiciary to protect citizens' rights."
    };

    public DashboardStatsDTO getDashboardStats(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int dayOfYear = LocalDate.now().getDayOfYear();
        String dailyFact = DAILY_FACTS[dayOfYear % DAILY_FACTS.length];

        return DashboardStatsDTO.builder()
                .articlesRead(user.getArticlesRead())
                .quizzesCompleted(user.getQuizzesCompleted())
                .points(user.getPoints())
                .streak(user.getStreak())
                .timeSpent(user.getTimeSpent())
                .dailyFact(dailyFact)
                .build();
    }
}
