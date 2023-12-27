package com.spring.Assignment2.service;

import com.spring.Assignment2.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories();

    List<CategoryDTO> getTopCategory();
}
