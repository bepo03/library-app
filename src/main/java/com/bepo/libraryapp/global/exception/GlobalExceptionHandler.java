package com.bepo.libraryapp.global.exception;

import com.bepo.libraryapp.global.common.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException e
    ) {
        return buildErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "잘못된 요청입니다.";

        return buildErrorResponse(ErrorCode.INVALID_INPUT_VALUE, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception e
    ) {
        return buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(
        ErrorCode errorCode,
        String message
    ) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode.getCode(), message));
    }
}
