package com.constitutional360.controller;

import com.constitutional360.dto.CreateDiscussionDTO;
import com.constitutional360.dto.DiscussionDTO;
import com.constitutional360.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discussions")
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionService discussionService;

    @GetMapping
    public ResponseEntity<List<DiscussionDTO>> getDiscussions(@RequestParam(required = false) String category) {
        return ResponseEntity.ok(discussionService.getAllDiscussions(category));
    }

    @PostMapping
    public ResponseEntity<DiscussionDTO> createDiscussion(Authentication auth, @RequestBody CreateDiscussionDTO dto) {
        return ResponseEntity.ok(discussionService.createDiscussion(auth.getName(), dto));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long id, Authentication auth, @RequestBody Map<String, String> body) {
        discussionService.addComment(id, auth.getName(), body.get("content"));
        return ResponseEntity.ok(Map.of("message", "Comment added"));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeDiscussion(@PathVariable Long id) {
        discussionService.likeDiscussion(id);
        return ResponseEntity.ok(Map.of("message", "Liked"));
    }
}
