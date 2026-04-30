package com.constitutional360.repository;

import com.constitutional360.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByUserIdOrderByTimestampDesc(Long userId);
    List<UserActivity> findByUserIdAndActivityType(Long userId, String activityType);
    long countByUserIdAndActivityType(Long userId, String activityType);
}
