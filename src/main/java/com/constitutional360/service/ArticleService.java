package com.constitutional360.service;

import com.constitutional360.entity.Article;
import com.constitutional360.entity.User;
import com.constitutional360.entity.UserActivity;
import com.constitutional360.repository.ArticleRepository;
import com.constitutional360.repository.UserActivityRepository;
import com.constitutional360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final UserActivityRepository activityRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getByCategory(String category) {
        return articleRepository.findByCategory(category);
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void markAsRead(Long articleId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if already read
        List<UserActivity> existing = activityRepository
                .findByUserIdAndActivityType(user.getId(), "ARTICLE_READ");
        boolean alreadyRead = existing.stream()
                .anyMatch(a -> articleId.equals(a.getReferenceId()));

        if (!alreadyRead) {
            UserActivity activity = UserActivity.builder()
                    .user(user)
                    .activityType("ARTICLE_READ")
                    .referenceId(articleId)
                    .details("Read article #" + articleId)
                    .build();
            activityRepository.save(activity);

            user.setArticlesRead(user.getArticlesRead() + 1);
            userRepository.save(user);
        }
    }
}
