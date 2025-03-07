package com.DCMetal.Shop.controller;

import com.DCMetal.Shop.model.Category;
import com.DCMetal.Shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> categories =categoryService.getAllCategories();
        return  new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category)
    {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created", HttpStatus.CREATED);

    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId)
    {
        try {
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with id: " +categoryId+" updated", HttpStatus.OK);

        }
        catch (ResponseStatusException e)
        {
            return  new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }

    }

    @DeleteMapping("/admin/categories/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryID)
    {
        try {
            String status = categoryService.deleteCategory(categoryID);
            //return new ResponseEntity<>(status, HttpStatus.OK);
            return  ResponseEntity.ok(status);

        }
        catch (ResponseStatusException e)
        {
            return  new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}
