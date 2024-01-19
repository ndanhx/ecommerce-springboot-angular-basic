package com.nguyenduyanh.ecommerce.controller;

import com.nguyenduyanh.ecommerce.dto.OrderDto;
import com.nguyenduyanh.ecommerce.dto.OrderedProductsResponseDto;
import com.nguyenduyanh.ecommerce.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TrackingController {

    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTracingId(@PathVariable UUID trackingId){
        OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
        if (orderDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDto);
    }


}
