package com.DCMetal.Shop.service;

import com.DCMetal.Shop.payload.CategoryDTO;
import com.DCMetal.Shop.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryID);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
