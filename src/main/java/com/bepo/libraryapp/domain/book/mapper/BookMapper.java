package com.bepo.libraryapp.domain.book.mapper;


import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Book toEntity(BookCreateRequest request);

    BookResponse toResponse(Book book);
}
