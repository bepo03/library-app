package com.bepo.libraryapp.domain.book.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@JsonPropertyOrder({"id", "userName", "bookName", "isReturn"})
@Builder
public record BookLoanResponse(Long id, String userName, String bookName, boolean isReturn) {
}
