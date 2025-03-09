package com.DCMetal.Shop.service;

import com.DCMetal.Shop.model.Category;
import com.DCMetal.Shop.payload.CategoryDTO;
import com.DCMetal.Shop.payload.CategoryResponse;

public interface CategoryService
{
    CategoryResponse getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    String deleteCategory(Long categoryID);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
