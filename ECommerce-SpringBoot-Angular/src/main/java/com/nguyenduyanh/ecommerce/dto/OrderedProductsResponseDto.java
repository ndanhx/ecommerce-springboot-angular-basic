package com.nguyenduyanh.ecommerce.dto;

import com.nguyenduyanh.ecommerce.entity.Product;
import com.nguyenduyanh.ecommerce.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
public class OrderedProductsResponseDto {

    private List<ProductDto> productDtoList;

    private Long orderAmount;


}
