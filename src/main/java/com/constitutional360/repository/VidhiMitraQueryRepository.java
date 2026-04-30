package com.constitutional360.repository;

import com.constitutional360.entity.VidhiMitraQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VidhiMitraQueryRepository extends JpaRepository<VidhiMitraQuery, Long> {
    List<VidhiMitraQuery> findByUserIdOrderByCreatedAtDesc(Long userId);
}
