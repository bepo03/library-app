package com.bepo.libraryapp.domain.user.controller;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.request.UserUpdateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserDeleteResponse;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.service.UserService;
import com.bepo.libraryapp.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // ========== CREATE ==========

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> saveUser(
            @RequestBody @Valid
            UserCreateRequest request
    ) {
        UserResponse response = userService.saveUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    // ========== READ ==========

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        List<UserResponse> responses = userService.getUsers();

        return ResponseEntity
                .ok(ApiResponse.success(responses));
    }

    // ========== UPDATE ==========

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @RequestBody @Valid
            UserUpdateRequest request
    ) {
        UserResponse response = userService.updateUser(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // ========== DELETE ==========

    @DeleteMapping
    public ResponseEntity<ApiResponse<UserDeleteResponse>> deleteUser(
            @RequestParam
            String name
    ) {
        UserDeleteResponse response = userService.deleteUser(name);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
