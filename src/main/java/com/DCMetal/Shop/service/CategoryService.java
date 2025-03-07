package com.DCMetal.Shop.service;

import com.DCMetal.Shop.model.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();

    void createCategory(Category category);

    String deleteCategory(Long categoryID);

    Category updateCategory(Category category, Long categoryId);
}
