package com.nguyenduyanh.ecommerce.repository;


import com.nguyenduyanh.ecommerce.dto.WishListDto;
import com.nguyenduyanh.ecommerce.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);

    Optional<WishList> findWishListByProductId(Long productId);

    Optional<WishList> findWishListByProductIdAndUserId(Long productId, Long userId);
}
