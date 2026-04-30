package com.constitutional360.controller;

import com.constitutional360.entity.Right;
import com.constitutional360.entity.Flashcard;
import com.constitutional360.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/rights")
    public ResponseEntity<List<Right>> getRights() {
        return ResponseEntity.ok(contentService.getAllRights());
    }

    @GetMapping("/flashcards")
    public ResponseEntity<List<Flashcard>> getFlashcards() {
        return ResponseEntity.ok(contentService.getAllFlashcards());
    }
}
