package com.constitutional360.repository;

import com.constitutional360.entity.User;
import com.constitutional360.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByOrderByPointsDesc();

    @Query("SELECT u FROM User u ORDER BY u.points DESC")
    List<User> findTopUsers();
}
