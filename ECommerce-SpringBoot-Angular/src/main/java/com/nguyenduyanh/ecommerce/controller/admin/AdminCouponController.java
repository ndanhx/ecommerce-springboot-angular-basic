package com.nguyenduyanh.ecommerce.controller.admin;

import com.nguyenduyanh.ecommerce.dto.CouponDto;
import com.nguyenduyanh.ecommerce.entity.Coupon;
import com.nguyenduyanh.ecommerce.exeptions.ValidationException;
import com.nguyenduyanh.ecommerce.services.admin.coupon.CouponService;
import com.nguyenduyanh.ecommerce.services.admin.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {
    private final CouponService couponService;


    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
        try {
            Coupon createCoupon = couponService.createCoupon(coupon);
            return ResponseEntity.ok(createCoupon);
        } catch (ValidationException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        return ResponseEntity.ok(couponService.getAllCoupons());

    }

}
