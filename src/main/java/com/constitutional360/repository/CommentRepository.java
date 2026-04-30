package com.constitutional360.repository;

import com.constitutional360.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByDiscussionIdOrderByCreatedAtAsc(Long discussionId);
    long countByDiscussionId(Long discussionId);
}
