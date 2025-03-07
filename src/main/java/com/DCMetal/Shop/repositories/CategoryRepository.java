package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
