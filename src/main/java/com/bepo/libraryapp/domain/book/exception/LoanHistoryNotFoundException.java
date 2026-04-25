package com.bepo.libraryapp.domain.book.exception;

import com.bepo.libraryapp.global.exception.BusinessException;
import com.bepo.libraryapp.global.exception.ErrorCode;

public class LoanHistoryNotFoundException extends BusinessException {
    public LoanHistoryNotFoundException(String userName, String bookName) {
        super(
                ErrorCode.LOAN_HISTORY_NOT_FOUND,
                "반납할 대출 이력을 찾을 수 없습니다. (userName: " + userName + ", bookName: " + bookName + ")"
        );
    }
}
