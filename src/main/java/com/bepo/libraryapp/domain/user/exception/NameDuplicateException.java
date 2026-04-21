package com.bepo.libraryapp.domain.user.exception;

import com.bepo.libraryapp.global.exception.BusinessException;
import com.bepo.libraryapp.global.exception.ErrorCode;

public class NameDuplicateException extends BusinessException {
    public NameDuplicateException(String name) {
        super(
                ErrorCode.DUPLICATE_NAME,
                "이미 사용 중인 이름 입니다. (name: " + name + ")"
        );
    }
}
