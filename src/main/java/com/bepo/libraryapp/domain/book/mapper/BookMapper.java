package com.bepo.libraryapp.domain.book.mapper;


import com.bepo.libraryapp.domain.book.dto.request.BookCreateRequest;
import com.bepo.libraryapp.domain.book.dto.response.BookResponse;
import com.bepo.libraryapp.domain.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Book toEntity(BookCreateRequest request);

    BookResponse toResponse(Book book);
}
