package com.nguyenduyanh.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nguyenduyanh.ecommerce.dto.OrderDto;
import com.nguyenduyanh.ecommerce.emuns.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.util.Lazy;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItems> cartItems;


    public OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setUsername(name);
        orderDto.setDate(date);
        orderDto.setAmount(amount);
        orderDto.setAddress(address);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setTrackingId(trackingId);
        if (coupon != null){
            orderDto.setCouponName(coupon.getName());
        }
        return orderDto;
    }
}
