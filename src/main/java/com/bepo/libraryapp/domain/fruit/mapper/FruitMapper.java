package com.bepo.libraryapp.domain.fruit.mapper;

import com.bepo.libraryapp.domain.fruit.Entity.Fruit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FruitMapper {

    @Select("SELECT * FROM fruits")
    List<Fruit> findAll();

    @Select("SELECT * FROM fruits WHERE name = #{name}")
    List<Fruit> findByName(@Param("name") String name);
}
