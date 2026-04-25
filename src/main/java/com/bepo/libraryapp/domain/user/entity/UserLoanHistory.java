package com.bepo.libraryapp.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "user_loan_history")
@Entity
@Getter
@NoArgsConstructor
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Column(nullable = false)
    private String bookName;

    private boolean isReturn = false;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    public static UserLoanHistory of(User user, String bookName) {
        UserLoanHistory userLoanHistory = new UserLoanHistory();
        userLoanHistory.user = user;
        userLoanHistory.bookName = bookName;
        return userLoanHistory;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}
