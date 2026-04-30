package com.constitutional360.repository;

import com.constitutional360.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
