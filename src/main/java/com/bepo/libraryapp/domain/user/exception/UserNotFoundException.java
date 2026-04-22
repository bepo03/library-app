package com.bepo.libraryapp.domain.user.exception;

import com.bepo.libraryapp.global.exception.BusinessException;
import com.bepo.libraryapp.global.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super(
                ErrorCode.USER_NOT_FOUND,
                "해당 유저를 찾을 수 없습니다, (id: " + id + ")"
        );
    }

    public UserNotFoundException(String name) {
        super(
                ErrorCode.USER_NOT_FOUND,
                "해당 유저를 찾을 수 없습니다, (name: " + name + ")"
        );
    }
}
