package com.constitutional360.service;

import com.constitutional360.entity.Right;
import com.constitutional360.entity.Flashcard;
import com.constitutional360.repository.RightRepository;
import com.constitutional360.repository.FlashcardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final RightRepository rightRepository;
    private final FlashcardRepository flashcardRepository;

    public List<Right> getAllRights() {
        return rightRepository.findAll();
    }

    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }
}
