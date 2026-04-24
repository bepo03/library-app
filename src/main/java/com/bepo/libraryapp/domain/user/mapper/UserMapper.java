package com.bepo.libraryapp.domain.user.mapper;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.request.UserUpdateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    User toEntity(UserCreateRequest request);

    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "age", ignore = true)
    })
    void updateEntity(UserUpdateRequest request, @MappingTarget User user);
}
