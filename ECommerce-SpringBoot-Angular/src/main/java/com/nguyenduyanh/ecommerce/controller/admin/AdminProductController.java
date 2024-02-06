package com.nguyenduyanh.ecommerce.controller.admin;

import com.nguyenduyanh.ecommerce.dto.FAQDto;
import com.nguyenduyanh.ecommerce.dto.ProductDto;
import com.nguyenduyanh.ecommerce.services.admin.faq.FAQService;
import com.nguyenduyanh.ecommerce.services.admin.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductService productService;
    private final FAQService faqService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name) {
        List<ProductDto> productDtos = productService.getAllProductByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDtos = productService.getProductById(productId);
        if (productDtos != null) {
            return ResponseEntity.ok(productDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable Long categoryId) {
        List<ProductDto> productDtos = productService.getAllProductByCategoryId(categoryId);
        if (productDtos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.updateProduct(productId, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productDto1);
    }

    //FAQ
    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
    }

}
