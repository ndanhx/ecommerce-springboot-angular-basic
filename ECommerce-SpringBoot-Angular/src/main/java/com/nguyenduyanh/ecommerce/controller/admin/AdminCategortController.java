package com.nguyenduyanh.ecommerce.controller.admin;


import com.nguyenduyanh.ecommerce.dto.CategoryDto;
import com.nguyenduyanh.ecommerce.entity.Category;
import com.nguyenduyanh.ecommerce.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategortController {

    private  final CategoryService categoryService;


    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createCategory(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return  ResponseEntity.ok(categoryService.getAllCategories());
    }


}
