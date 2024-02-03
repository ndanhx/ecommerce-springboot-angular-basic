package com.nguyenduyanh.ecommerce.controller.admin;


import com.nguyenduyanh.ecommerce.dto.CategoryDto;
import com.nguyenduyanh.ecommerce.dto.ProductDto;
import com.nguyenduyanh.ecommerce.entity.Category;
import com.nguyenduyanh.ecommerce.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private  final CategoryService categoryService;


    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createCategory(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return  ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId){
        boolean deleted = categoryService.deleteCategoryById(categoryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        if (categoryDto != null) {
            return ResponseEntity.ok(categoryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @ModelAttribute CategoryDto categoryDto)  {
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto1);
    }

}
