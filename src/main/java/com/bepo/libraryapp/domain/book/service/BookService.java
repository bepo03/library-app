package com.bepo.libraryapp.domain.book.service;

import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.request.BookLoanRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookLoanResponse;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.entity.Book;
import com.bepo.libraryapp.domain.book.mapper.BookMapper;
import com.bepo.libraryapp.domain.book.repository.BookRepository;
import com.bepo.libraryapp.domain.user.entity.User;
import com.bepo.libraryapp.domain.user.entity.UserLoanHistory;
import com.bepo.libraryapp.domain.user.repository.UserLoanHistoryRepository;
import com.bepo.libraryapp.domain.user.repository.UserRepository;
import com.bepo.libraryapp.global.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    // ========== CREATE ==========

    public BookResponse saveBook(BookCreateRequest request) {
        log.info("새로운 책 생성: {}", request.getName());
        Book book = bookMapper.toEntity(request);

        validateDuplicateBook(book);

        Book savedBook = bookRepository.save(book);
        log.info("책 생성 성공: ID: {}", savedBook.getId());

        return bookMapper.toResponse(savedBook);
    }

    public BookLoanResponse loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("진짜 대출되어 있는 책입니다.");
        }

        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        UserLoanHistory userLoanHistory = new UserLoanHistory(user.getId(), book.getName());
        UserLoanHistory saveUserLoanHistory = userLoanHistoryRepository.save(userLoanHistory);

        return BookLoanResponse.builder()
                .userName(user.getName())
                .bookName(saveUserLoanHistory.getBookName())
                .isReturn(saveUserLoanHistory.isReturn())
                .build();
    }

    // ========== PRIVATE METHODS ==========

    private void validateDuplicateBook(Book book) {
        bookRepository.findByName(book.getName())
                .ifPresent(foundBook -> {
                    throw new NameDuplicateException(foundBook.getName());
                });
    }
}
