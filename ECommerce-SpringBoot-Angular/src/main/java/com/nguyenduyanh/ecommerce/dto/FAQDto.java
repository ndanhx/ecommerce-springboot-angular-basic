package com.nguyenduyanh.ecommerce.dto;

import com.nguyenduyanh.ecommerce.entity.Product;
import lombok.Data;

@Data
public class FAQDto {

    private Long id;

    private String question;
    private String answer;


    private Long productId;

}
