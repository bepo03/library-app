package com.bepo.libraryapp.domain.fruit.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fruits")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
