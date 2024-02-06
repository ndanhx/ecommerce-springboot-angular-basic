package com.nguyenduyanh.ecommerce.controller.admin;

import com.nguyenduyanh.ecommerce.dto.AnalyticsResponseDto;
import com.nguyenduyanh.ecommerce.dto.OrderDto;
import com.nguyenduyanh.ecommerce.services.admin.order.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {
    private final AdminOrderService orderService;

    @GetMapping("/placed-orders")
    public ResponseEntity<List<OrderDto>> getAllPlaceOrders(){
        return ResponseEntity.ok(orderService.getAllPlaceOrders());

    }

    @GetMapping("/change-order-status/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status ){
        OrderDto orderDto = orderService.changeOrderStatus(orderId, status);
        if (orderDto == null) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

        }
        return  ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponseDto> getAnalytics(){
        return  ResponseEntity.ok(orderService.calculatorAnalytics());
    }




}
