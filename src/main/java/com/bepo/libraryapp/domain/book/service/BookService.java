package com.bepo.libraryapp.domain.book.service;

import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.request.BookLoanRequest;
import com.bepo.libraryapp.domain.book.dto.request.BookReturnRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookLoanResponse;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.dto.response.BookReturnResponse;
import com.bepo.libraryapp.domain.book.entity.Book;
import com.bepo.libraryapp.domain.book.exception.BookAlreadyLoanedException;
import com.bepo.libraryapp.domain.book.exception.BookNotFoundException;
import com.bepo.libraryapp.domain.book.exception.LoanHistoryNotFoundException;
import com.bepo.libraryapp.domain.book.mapper.BookMapper;
import com.bepo.libraryapp.domain.book.repository.BookRepository;
import com.bepo.libraryapp.domain.user.entity.User;
import com.bepo.libraryapp.domain.user.entity.UserLoanHistory;
import com.bepo.libraryapp.domain.user.exception.UserNotFoundException;
import com.bepo.libraryapp.domain.user.repository.UserLoanHistoryRepository;
import com.bepo.libraryapp.domain.user.repository.UserRepository;
import com.bepo.libraryapp.global.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
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
                .orElseThrow(() -> new BookNotFoundException(request.getBookName()));

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new BookAlreadyLoanedException(book.getName());
        }

        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new UserNotFoundException(request.getUserName()));

        UserLoanHistory userLoanHistory = UserLoanHistory.of(user, book.getName());
        UserLoanHistory saveUserLoanHistory = userLoanHistoryRepository.save(userLoanHistory);

        return BookLoanResponse.builder()
                .id(saveUserLoanHistory.getId())
                .userName(user.getName())
                .bookName(saveUserLoanHistory.getBookName())
                .isReturn(saveUserLoanHistory.isReturn())
                .build();
    }

    // ========== UPDATE ==========

    public BookReturnResponse returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new UserNotFoundException(request.getUserName()));

        UserLoanHistory history = userLoanHistoryRepository
                .findByUser_IdAndBookNameAndIsReturn(user.getId(), request.getBookName(), false)
                .orElseThrow(() -> new LoanHistoryNotFoundException(
                        request.getUserName(),
                        request.getBookName()
                ));

        history.doReturn();

        return BookReturnResponse.builder()
                .id(history.getId())
                .userName(user.getName())
                .bookName(history.getBookName())
                .isReturn(history.isReturn())
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
