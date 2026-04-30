package com.constitutional360.repository;

import com.constitutional360.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(String category);
    List<Article> findByTitleContainingIgnoreCaseOrArticleNumberContainingIgnoreCase(String title, String articleNumber);
}
