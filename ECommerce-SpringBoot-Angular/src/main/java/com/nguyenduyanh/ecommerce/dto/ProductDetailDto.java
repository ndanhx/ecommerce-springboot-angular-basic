package com.nguyenduyanh.ecommerce.dto;

import com.nguyenduyanh.ecommerce.entity.FAQ;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDetailDto {
    private ProductDto  productDto;

    private List<ReviewDto> reviewDtoList;

    private List<FAQDto> faqDtoList;
}
