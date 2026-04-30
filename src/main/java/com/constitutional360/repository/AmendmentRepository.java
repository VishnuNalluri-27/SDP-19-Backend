package com.constitutional360.repository;

import com.constitutional360.entity.Amendment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmendmentRepository extends JpaRepository<Amendment, Long> {
    List<Amendment> findAllByOrderByYearAsc();
}
