package com.nguyenduyanh.ecommerce.services.customer.wishlist;

import com.nguyenduyanh.ecommerce.dto.WishListDto;
import com.nguyenduyanh.ecommerce.entity.Product;
import com.nguyenduyanh.ecommerce.entity.User;
import com.nguyenduyanh.ecommerce.entity.WishList;
import com.nguyenduyanh.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private final WishListRepository wishListRepository;


    @Override
    public WishListDto addProductToWishList(WishListDto wishListDto) {
        Optional<Product> optionalProduct = productRepository.findById(wishListDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Optional<WishList> optionalWishListByProductAndUser = wishListRepository.findWishListByProductIdAndUserId(optionalProduct.get().getId(), optionalUser.get().getId());
            if (optionalWishListByProductAndUser.isPresent() ) {
                return optionalWishListByProductAndUser.get().getWishListDto();
            }
            WishList wishList = new WishList();
            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());
            return wishListRepository.save(wishList).getWishListDto();
        }
        return null;
    }

    @Override
    public List<WishListDto> findAllWishListByUserId(Long userId) {
        return wishListRepository.findAllByUserId(userId).stream().map(WishList::getWishListDto).collect(Collectors.toList());
    }

    @Override
    public WishListDto checkProductWishListInUser(Long userId, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Optional<WishList> optionalWishList = wishListRepository.findWishListByProductIdAndUserId(
                    optionalProduct.get().getId(), optionalUser.get().getId());
            if (optionalWishList.isPresent()) {
                return optionalWishList.get().getWishListDto();
            }

        }
        return null;
    }

    @Override
    public boolean deleteWishList(Long userId,Long wishListId) {
        Optional<WishList> optionalWishList = wishListRepository.findById(wishListId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalWishList.isPresent() && optionalUser.isPresent()) {
            wishListRepository.delete(optionalWishList.get());
            return true;
        }
        return false;
    }
}
