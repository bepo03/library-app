package com.bepo.libraryapp.domain.fruit.repository;

import com.bepo.libraryapp.domain.fruit.Entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

}
