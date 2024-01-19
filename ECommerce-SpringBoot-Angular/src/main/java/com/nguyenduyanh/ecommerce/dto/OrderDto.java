package com.nguyenduyanh.ecommerce.dto;

import com.nguyenduyanh.ecommerce.emuns.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;

    private String name;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;



    private String username;


    private List<CartItemsDto> cartItems;

    private String couponName;
}
