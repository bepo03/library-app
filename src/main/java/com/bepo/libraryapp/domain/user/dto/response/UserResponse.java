package com.bepo.libraryapp.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@JsonPropertyOrder({"id", "name", "age"})
@Builder
public record UserResponse(Long id, String name, Integer age) {
}
