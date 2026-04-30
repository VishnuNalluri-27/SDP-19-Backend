package com.constitutional360.repository;

import com.constitutional360.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByUserIdOrderByAttemptedAtDesc(Long userId);
    List<QuizResult> findByUserIdAndQuizId(Long userId, Long quizId);
}
