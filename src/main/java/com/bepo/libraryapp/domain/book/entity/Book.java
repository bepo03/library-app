package com.bepo.libraryapp.domain.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "books")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
