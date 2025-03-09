package com.DCMetal.Shop.service;

import com.DCMetal.Shop.exceptions.APIException;
import com.DCMetal.Shop.exceptions.ResourceNotFoundException;
import com.DCMetal.Shop.model.Category;
import com.DCMetal.Shop.payload.CategoryDTO;
import com.DCMetal.Shop.payload.CategoryResponse;
import com.DCMetal.Shop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories()
    {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw  new APIException("Category List is empty");
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw  new APIException("Category with the name "+category.getCategoryName()+" already exists");
        Category createCategory = categoryRepository.save(category);
        CategoryDTO createdCategoryDTO = modelMapper.map(createCategory, CategoryDTO.class);
        return createdCategoryDTO;
    }

    @Override
    public String deleteCategory(Long categoryID)
    {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryID));

        categoryRepository.delete(category);
        return "Category with id " + categoryID + " deleted";
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryID)
    {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw  new APIException("Category with the name "+savedCategory.getCategoryName()+" already exists with id "+savedCategory.getCategoryId());
        Category updateCategory = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryID",categoryID));
        category.setCategoryId(categoryID);
        updateCategory=categoryRepository.save(category);
        return modelMapper.map(updateCategory, CategoryDTO.class);
    }
}
