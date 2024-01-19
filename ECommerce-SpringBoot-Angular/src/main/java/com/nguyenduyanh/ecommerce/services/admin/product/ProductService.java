package com.nguyenduyanh.ecommerce.services.admin.product;

import com.nguyenduyanh.ecommerce.dto.ProductDto;
import com.nguyenduyanh.ecommerce.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductByName(String title);

    boolean deleteProduct(Long id);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}
