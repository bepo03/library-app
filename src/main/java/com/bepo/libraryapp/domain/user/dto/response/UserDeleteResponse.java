package com.bepo.libraryapp.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"message", "deletedName"})
public class UserDeleteResponse {
    private String message;
    private String deletedName;

    public static UserDeleteResponse of(String name, Boolean isDeleted) {
        if (isDeleted) {
            return UserDeleteResponse.builder()
                    .message("사용자가 성공적으로 삭제되었습니다.")
                    .deletedName(name)
                    .build();
        } else {
            return UserDeleteResponse.builder()
                    .message("사용자 삭제가 실패했습니다.")
                    .build();
        }
    }
}
