package com.bepo.libraryapp.domain.user.mapper;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.request.UserUpdateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        return User.of(request.getName(), request.getAge());
    }

    UserResponse toResponse(User user);

    default void updateEntity(UserUpdateRequest request, User user) {
        if (request == null || user == null) {
            return;
        }
        user.updateName(request.getName());
    }
}
