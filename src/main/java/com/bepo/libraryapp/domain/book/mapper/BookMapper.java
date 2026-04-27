package com.bepo.libraryapp.domain.book.mapper;


import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    default Book toEntity(BookCreateRequest request) {
        if (request == null) {
            return null;
        }
        return Book.of(request.getName());
    }

    BookResponse toResponse(Book book);
}
