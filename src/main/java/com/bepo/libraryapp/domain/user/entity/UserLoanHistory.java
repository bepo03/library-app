package com.bepo.libraryapp.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_loan_history")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String bookName;

    @Builder.Default
    private boolean isReturn = false;

    public UserLoanHistory(Long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
    }
}
