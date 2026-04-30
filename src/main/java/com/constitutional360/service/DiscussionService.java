package com.constitutional360.service;

import com.constitutional360.dto.CreateDiscussionDTO;
import com.constitutional360.dto.DiscussionDTO;
import com.constitutional360.entity.Comment;
import com.constitutional360.entity.Discussion;
import com.constitutional360.entity.User;
import com.constitutional360.repository.CommentRepository;
import com.constitutional360.repository.DiscussionRepository;
import com.constitutional360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<DiscussionDTO> getAllDiscussions(String category) {
        List<Discussion> discussions;
        if (category != null && !category.isEmpty() && !category.equals("All")) {
            discussions = discussionRepository.findByCategoryOrderByCreatedAtDesc(category);
        } else {
            discussions = discussionRepository.findAllByOrderByCreatedAtDesc();
        }
        return discussions.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DiscussionDTO createDiscussion(String email, CreateDiscussionDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Discussion discussion = Discussion.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(dto.getCategory())
                .build();

        discussion = discussionRepository.save(discussion);
        return toDTO(discussion);
    }

    public void addComment(Long discussionId, String email, String content) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new RuntimeException("Discussion not found"));

        Comment comment = Comment.builder()
                .discussion(discussion)
                .user(user)
                .content(content)
                .build();
        commentRepository.save(comment);

        discussion.setReplyCount((int) commentRepository.countByDiscussionId(discussionId));
        discussionRepository.save(discussion);
    }

    public void likeDiscussion(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new RuntimeException("Discussion not found"));
        discussion.setLikes(discussion.getLikes() + 1);
        discussionRepository.save(discussion);
    }

    private DiscussionDTO toDTO(Discussion d) {
        String preview = d.getContent();
        if (preview != null && preview.length() > 200) {
            preview = preview.substring(0, 200) + "...";
        }
        return DiscussionDTO.builder()
                .id(d.getId())
                .title(d.getTitle())
                .content(d.getContent())
                .author(d.getUser().getName())
                .role(d.getUser().getRole().name().replace("ROLE_", "").toLowerCase())
                .category(d.getCategory())
                .date(d.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .replies(d.getReplyCount())
                .likes(d.getLikes())
                .preview(preview)
                .build();
    }
}
