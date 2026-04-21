package com.bepo.libraryapp.global.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"success", "data", "error"})
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorInfo error;

    private ApiResponse() {}

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.error = null;
        return response;
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.data = null;
        response.error = new ErrorInfo(code, message);
        return response;
    }

    private record ErrorInfo(String code, String message) {}
}
