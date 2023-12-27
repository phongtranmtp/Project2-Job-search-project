package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from category \n" +
            "group by id\n" +
            "order by number_choose desc\n" +
            "limit 4",nativeQuery = true)
    List<Category> getTopCategory();

}