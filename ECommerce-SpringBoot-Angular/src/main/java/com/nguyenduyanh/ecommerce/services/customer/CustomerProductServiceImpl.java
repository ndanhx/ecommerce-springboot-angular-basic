package com.nguyenduyanh.ecommerce.services.customer;

import com.nguyenduyanh.ecommerce.dto.ProductDetailDto;
import com.nguyenduyanh.ecommerce.dto.ProductDto;
import com.nguyenduyanh.ecommerce.entity.FAQ;
import com.nguyenduyanh.ecommerce.entity.Product;
import com.nguyenduyanh.ecommerce.entity.Review;
import com.nguyenduyanh.ecommerce.entity.User;
import com.nguyenduyanh.ecommerce.repository.CategoryRepository;
import com.nguyenduyanh.ecommerce.repository.FAQRepository;
import com.nguyenduyanh.ecommerce.repository.ProductRepository;
import com.nguyenduyanh.ecommerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{


    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final FAQRepository faqRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductByName(String title) {
        List<Product> products = productRepository.findAllByNameContaining(title);
        return products.stream().map(Product::getDto ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        return products.stream().map(Product::getDto ).collect(Collectors.toList());

    }

    @Override
    public ProductDetailDto getProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setProductDto(optionalProduct.get().getDto());
            productDetailDto.setFaqDtoList(faqList.stream()
                    .map(FAQ::getFAQDto)
                    .collect(Collectors.toList()));
            productDetailDto.setReviewDtoList(reviewList.stream()
                    .map(Review::getReviewDto)
                    .collect(Collectors.toList()));

            return productDetailDto;
        }
        return null;

    }
}
