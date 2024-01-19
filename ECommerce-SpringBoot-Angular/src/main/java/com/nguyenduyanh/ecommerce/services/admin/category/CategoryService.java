package com.nguyenduyanh.ecommerce.services.admin.category;

import com.nguyenduyanh.ecommerce.dto.CategoryDto;
import com.nguyenduyanh.ecommerce.entity.Category;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();
}
