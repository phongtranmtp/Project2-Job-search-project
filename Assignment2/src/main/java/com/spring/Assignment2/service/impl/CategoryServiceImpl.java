package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.CategoryRepository;
import com.spring.Assignment2.entity.Category;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.CategoryDTO;
import com.spring.Assignment2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    /* lấy ra danh sách category */
    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(convertCat(category));
        }
        return categoryDTOs;
    }

    /* lấy ra danh sách top category được chọn nhiều nhất */
    @Override
    public List<CategoryDTO> getTopCategory() {
        List<Category> categories = categoryRepository.getTopCategory();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(convertCat(category));
        }
        return categoryDTOs;
    }

    public CategoryDTO convertCat(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setRecruitments(category.getRecruitments());
        categoryDTO.setNumberChoose(category.getNumberChoose());
        return categoryDTO;
    }
}
