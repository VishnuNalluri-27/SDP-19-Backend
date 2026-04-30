package com.constitutional360.service;

import com.constitutional360.dto.QuizDTO;
import com.constitutional360.dto.QuizSubmitDTO;
import com.constitutional360.entity.*;
import com.constitutional360.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public QuizDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return toDTO(quiz);
    }

    public void submitQuiz(String email, QuizSubmitDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        QuizResult result = QuizResult.builder()
                .user(user)
                .quiz(quiz)
                .score(dto.getScore())
                .totalQuestions(dto.getTotalQuestions())
                .build();
        quizResultRepository.save(result);

        // Update user stats
        user.setQuizzesCompleted(user.getQuizzesCompleted() + 1);
        int pointsEarned = dto.getScore() * 20; // 20 points per correct answer
        user.setPoints(user.getPoints() + pointsEarned);
        userRepository.save(user);
    }

    public List<QuizResult> getUserHistory(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return quizResultRepository.findByUserIdOrderByAttemptedAtDesc(user.getId());
    }

    private QuizDTO toDTO(Quiz quiz) {
        List<Question> questions = questionRepository.findByQuizId(quiz.getId());
        List<QuizDTO.QuestionDTO> questionDTOs = questions.stream()
                .map(q -> {
                    List<String> options;
                    try {
                        options = objectMapper.readValue(q.getOptions(), new TypeReference<List<String>>() {});
                    } catch (Exception e) {
                        options = List.of();
                    }
                    return QuizDTO.QuestionDTO.builder()
                            .id(q.getId())
                            .question(q.getQuestionText())
                            .options(options)
                            .correct(q.getCorrectIndex())
                            .explanation(q.getExplanation())
                            .build();
                })
                .collect(Collectors.toList());

        return QuizDTO.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .difficulty(quiz.getDifficulty())
                .questions(questionDTOs)
                .build();
    }
}
