package com.bepo.libraryapp.domain.book.exception;

import com.bepo.libraryapp.global.exception.BusinessException;
import com.bepo.libraryapp.global.exception.ErrorCode;

public class BookAlreadyLoanedException extends BusinessException {
    public BookAlreadyLoanedException(String name) {
        super(
                ErrorCode.BOOK_ALREADY_LOANED,
                "이미 대출 중인 책입니다. (name: " + name + ")"
        );
    }
}
