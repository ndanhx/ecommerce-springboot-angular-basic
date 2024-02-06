package com.nguyenduyanh.ecommerce.controller.customer;

import com.nguyenduyanh.ecommerce.dto.OrderedProductsResponseDto;
import com.nguyenduyanh.ecommerce.dto.WishListDto;
import com.nguyenduyanh.ecommerce.services.customer.wishlist.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerWishListController {

    private final WishListService wishListService;



    @PostMapping("/wish-list")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto)   {
        WishListDto postedWishListDto = wishListService.addProductToWishList(wishListDto);
        if (postedWishListDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishListDto);
    }
    @GetMapping("/wish-list/{userId}")
    public ResponseEntity<List<WishListDto>> getAllWishListByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishListService.findAllWishListByUserId(userId));
    }

    @GetMapping("/wish-list/{userId}/{productId}")
    public ResponseEntity<WishListDto> checkProductWishListInUser(@PathVariable Long userId, @PathVariable Long productId){
        return ResponseEntity.ok(wishListService.checkProductWishListInUser(userId,productId));
    }

    @DeleteMapping("/wish-list/{userId}/{wishListId}")
    public ResponseEntity<Void> deleteWishList(@PathVariable Long wishListId,  @PathVariable Long userId) {
        boolean deleted = wishListService.deleteWishList(userId,wishListId);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
