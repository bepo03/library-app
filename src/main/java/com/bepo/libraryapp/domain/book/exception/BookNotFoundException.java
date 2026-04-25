package com.bepo.libraryapp.domain.book.exception;

import com.bepo.libraryapp.global.exception.BusinessException;
import com.bepo.libraryapp.global.exception.ErrorCode;

public class BookNotFoundException extends BusinessException {
    public BookNotFoundException(String name) {
        super(
                ErrorCode.BOOK_NOT_FOUND,
                "해당 책을 찾을 수 없습니다. (name: " + name + ")"
        );
    }
}
