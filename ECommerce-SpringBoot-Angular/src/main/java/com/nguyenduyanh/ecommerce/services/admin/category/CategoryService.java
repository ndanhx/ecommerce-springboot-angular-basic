package com.nguyenduyanh.ecommerce.services.admin.category;

import com.nguyenduyanh.ecommerce.dto.CategoryDto;
import com.nguyenduyanh.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

    boolean deleteCategoryById(Long categoryId);

    CategoryDto getCategoryById(Long categoryId);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);
}
