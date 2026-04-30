package com.constitutional360.service;

import com.constitutional360.entity.Amendment;
import com.constitutional360.repository.AmendmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmendmentService {

    private final AmendmentRepository amendmentRepository;

    public List<Amendment> getAllAmendments() {
        return amendmentRepository.findAllByOrderByYearAsc();
    }

    public Amendment createAmendment(Amendment amendment) {
        return amendmentRepository.save(amendment);
    }

    public Amendment updateAmendment(Long id, Amendment updated) {
        Amendment existing = amendmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amendment not found"));
        existing.setYear(updated.getYear());
        existing.setNumber(updated.getNumber());
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        return amendmentRepository.save(existing);
    }
}
