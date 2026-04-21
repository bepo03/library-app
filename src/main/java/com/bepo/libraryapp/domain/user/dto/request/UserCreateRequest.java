package com.bepo.libraryapp.domain.user.dto.request;

import com.bepo.libraryapp.domain.user.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private final String name;

    @Min(value = 1, message = "나이는 1살 이상이어야 합니다.")
    private final Integer age;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .age(this.age)
                .build();
    }
}
