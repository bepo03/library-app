package com.bepo.libraryapp.domain.fruit.controller;

import com.bepo.libraryapp.domain.fruit.Entity.Fruit;
import com.bepo.libraryapp.domain.fruit.mapper.FruitMapper;
import com.bepo.libraryapp.domain.fruit.repository.FruitJdbcRepository;
import com.bepo.libraryapp.domain.fruit.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fruit")
public class FruitController {
    private final FruitMapper fruitMapper;
    private final FruitRepository fruitRepository;
    private final FruitJdbcRepository fruitJdbcRepository;

    @GetMapping("/mybatis")
    public void findAll() {
        List<Fruit> fruits = fruitMapper.findByName("apple");
        for (Fruit fruit : fruits) {
            System.out.println(fruit.getName());
            System.out.println(fruit.getPrice());
        }
    }

    // 248_944ms
    @GetMapping("/save1")
    public void save1() {
        List<Fruit> fruits = prepareFruits();
        long startMillis = System.currentTimeMillis();

        fruitRepository.saveAll(fruits);
        System.out.printf("소요 시간, %sms\n", System.currentTimeMillis() - startMillis);
    }

    // 2_070ms
    @GetMapping("/save2")
    public void save2() {
        List<Fruit> fruits = prepareFruits();
        long startMillis = System.currentTimeMillis();

        fruitJdbcRepository.saveAllBatch(fruits);
        System.out.printf("소요 시간, %sms\n", System.currentTimeMillis() - startMillis);
    }

    private List<Fruit> prepareFruits() {
        List<Fruit> fruits = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100_000; i++) {
            fruits.add(new Fruit("사과", random.nextInt(20_000)));
        }

        return fruits;
    }
}
