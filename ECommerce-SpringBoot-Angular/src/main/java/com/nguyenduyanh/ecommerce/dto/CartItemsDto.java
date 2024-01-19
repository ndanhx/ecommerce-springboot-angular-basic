package com.nguyenduyanh.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemsDto {
    private Long id;

    private Long price;

    private Long quantity;

    private Long userId;

    private Long productId;

    private Long OrderId;
    private String productName;

    private byte[] returnedImg;

}
