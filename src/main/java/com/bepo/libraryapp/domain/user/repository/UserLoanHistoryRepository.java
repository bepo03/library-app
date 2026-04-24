package com.bepo.libraryapp.domain.user.repository;

import com.bepo.libraryapp.domain.user.entity.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    boolean existsByBookNameAndIsReturn(String name, Boolean isReturn);
}
