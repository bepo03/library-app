package com.bepo.libraryapp.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private Long id;
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;
}
