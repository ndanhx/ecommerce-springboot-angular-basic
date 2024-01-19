package com.nguyenduyanh.ecommerce.services.customer.review;

import com.nguyenduyanh.ecommerce.dto.OrderedProductsResponseDto;
import com.nguyenduyanh.ecommerce.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
