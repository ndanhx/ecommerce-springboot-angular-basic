package com.nguyenduyanh.ecommerce.services.admin.coupon;

import com.nguyenduyanh.ecommerce.entity.Coupon;

import java.util.List;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
