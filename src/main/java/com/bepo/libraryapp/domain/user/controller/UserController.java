package com.bepo.libraryapp.domain.user.controller;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.request.UserUpdateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserDeleteResponse;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.service.UserService;
import com.bepo.libraryapp.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> saveUser(
            @RequestBody
            UserCreateRequest request
    ) {
        UserResponse response = userService.saveUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        List<UserResponse> responses = userService.getUser();

        return ResponseEntity
                .ok(ApiResponse.success(responses));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @RequestBody
            UserUpdateRequest request
    ) {
        UserResponse response = userService.updateUser(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<UserDeleteResponse>> deleteUser(
            @RequestParam
            String name
    ) {
        UserDeleteResponse response = userService.deleteUser(name);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
