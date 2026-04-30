package com.constitutional360.controller;

import com.constitutional360.entity.Article;
import com.constitutional360.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty() && !category.equals("All")) {
            return ResponseEntity.ok(articleService.getByCategory(category));
        }
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id, Authentication auth) {
        articleService.markAsRead(id, auth.getName());
        return ResponseEntity.ok(Map.of("message", "Article marked as read"));
    }
}
