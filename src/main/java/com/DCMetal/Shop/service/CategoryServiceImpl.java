package com.DCMetal.Shop.service;

import com.DCMetal.Shop.model.Category;
import com.DCMetal.Shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    //private List<Category> categories =new ArrayList<Category>();
    //private long nextId=1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category)
    {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryID)
    {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        categoryRepository.delete(category);
        return "Category with id " + categoryID + " deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryID)
    {
        Category savedCategory = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        category.setCategoryId(categoryID);
        savedCategory=categoryRepository.save(category);
        return savedCategory;
    }
}
