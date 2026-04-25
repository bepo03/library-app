package com.bepo.libraryapp.domain.user.repository;

import com.bepo.libraryapp.domain.user.entity.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    boolean existsByBookNameAndIsReturn(String name, Boolean isReturn);

    Optional<UserLoanHistory> findByUser_IdAndBookNameAndIsReturn(Long userId, String bookName, boolean isReturn);
}
