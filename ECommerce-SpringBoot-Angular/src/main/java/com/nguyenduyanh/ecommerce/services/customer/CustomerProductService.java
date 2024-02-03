package com.nguyenduyanh.ecommerce.services.customer;

import com.nguyenduyanh.ecommerce.dto.ProductDetailDto;
import com.nguyenduyanh.ecommerce.dto.ProductDto;
import com.nguyenduyanh.ecommerce.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerProductService {

    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductByName(String title);

    List<ProductDto> getAllProductByCategoryId(Long categoryId);


    ProductDetailDto getProductDetailById(Long productId);


}
