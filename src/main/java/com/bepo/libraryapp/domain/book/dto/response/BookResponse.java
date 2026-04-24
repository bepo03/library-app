package com.bepo.libraryapp.domain.book.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@JsonPropertyOrder({"id", "name"})
@Builder
public record BookResponse(Long id, String name) {
}