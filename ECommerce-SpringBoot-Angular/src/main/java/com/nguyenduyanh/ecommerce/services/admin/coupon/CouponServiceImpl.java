package com.nguyenduyanh.ecommerce.services.admin.coupon;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.nguyenduyanh.ecommerce.entity.Coupon;
import com.nguyenduyanh.ecommerce.exeptions.ValidationException;
import com.nguyenduyanh.ecommerce.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon){
        if (couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists");
        }
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return  couponRepository.findAll();
    }
}
