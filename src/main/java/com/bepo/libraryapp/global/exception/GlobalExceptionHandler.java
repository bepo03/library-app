package com.bepo.libraryapp.global.exception;

import com.bepo.libraryapp.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception e
    ) {
        return buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
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
