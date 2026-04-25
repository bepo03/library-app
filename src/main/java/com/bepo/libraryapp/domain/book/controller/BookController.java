package com.bepo.libraryapp.domain.book.controller;

import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.request.BookLoanRequest;
import com.bepo.libraryapp.domain.book.dto.request.BookReturnRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookLoanResponse;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.dto.response.BookReturnResponse;
import com.bepo.libraryapp.domain.book.service.BookService;
import com.bepo.libraryapp.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    // ========== CREATE ==========

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> saveBook(
            @RequestBody @Valid
            BookCreateRequest request
    ) {
        BookResponse response = bookService.saveBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @PostMapping("/loan")
    public ResponseEntity<ApiResponse<BookLoanResponse>> loanBook(
            @RequestBody @Valid
            BookLoanRequest request
    ) {
        BookLoanResponse response = bookService.loanBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    // ========== UPDATE ==========

    @PutMapping("/return")
    public ResponseEntity<ApiResponse<BookReturnResponse>> returnBook(
            @RequestBody @Valid
            BookReturnRequest request
    ) {
        BookReturnResponse response = bookService.returnBook(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
