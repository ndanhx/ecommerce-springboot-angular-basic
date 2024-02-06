package com.nguyenduyanh.ecommerce.services.customer.wishlist;

import com.nguyenduyanh.ecommerce.dto.WishListDto;

import java.util.List;

public interface     WishListService {

    WishListDto addProductToWishList(WishListDto wishListDto);

    List<WishListDto> findAllWishListByUserId(Long userId);

    WishListDto checkProductWishListInUser(Long userId, Long productId);

    boolean deleteWishList( Long userId,Long wishListId);

}
