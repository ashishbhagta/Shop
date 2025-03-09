package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

    Category findByCategoryName(@NotBlank String categoryName);
}
