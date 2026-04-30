package com.constitutional360.repository;

import com.constitutional360.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findAllByOrderByCreatedAtDesc();
    List<Discussion> findByCategoryOrderByCreatedAtDesc(String category);
}
