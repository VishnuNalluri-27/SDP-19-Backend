package com.constitutional360.controller;

import com.constitutional360.entity.Amendment;
import com.constitutional360.service.AmendmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amendments")
@RequiredArgsConstructor
public class AmendmentController {

    private final AmendmentService amendmentService;

    @GetMapping
    public ResponseEntity<List<Amendment>> getAllAmendments() {
        return ResponseEntity.ok(amendmentService.getAllAmendments());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Amendment> createAmendment(@RequestBody Amendment amendment) {
        return ResponseEntity.ok(amendmentService.createAmendment(amendment));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Amendment> updateAmendment(@PathVariable Long id, @RequestBody Amendment amendment) {
        return ResponseEntity.ok(amendmentService.updateAmendment(id, amendment));
    }
}
