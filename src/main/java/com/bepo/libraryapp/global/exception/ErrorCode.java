package com.bepo.libraryapp.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID_INPUT_VALUE"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"),
    DUPLICATE_NAME(HttpStatus.CONFLICT, "DUPLICATE_NAME"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND"),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "BOOK_NOT_FOUND"),
    BOOK_ALREADY_LOANED(HttpStatus.CONFLICT, "BOOK_ALREADY_LOANED"),
    LOAN_HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "LOAN_HISTORY_NOT_FOUND");

    private final HttpStatus httpStatus;
    private final String code;

    ErrorCode(HttpStatus httpStatus, String code) {
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
