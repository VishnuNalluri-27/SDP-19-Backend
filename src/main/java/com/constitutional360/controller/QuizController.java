package com.constitutional360.controller;

import com.constitutional360.dto.QuizDTO;
import com.constitutional360.dto.QuizSubmitDTO;
import com.constitutional360.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(Authentication auth, @RequestBody QuizSubmitDTO dto) {
        quizService.submitQuiz(auth.getName(), dto);
        return ResponseEntity.ok(Map.of("message", "Quiz submitted successfully"));
    }
}
