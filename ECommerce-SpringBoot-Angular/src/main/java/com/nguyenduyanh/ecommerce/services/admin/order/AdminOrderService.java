package com.nguyenduyanh.ecommerce.services.admin.order;

import com.nguyenduyanh.ecommerce.dto.AnalyticsResponseDto;
import com.nguyenduyanh.ecommerce.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlaceOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponseDto calculatorAnalytics();


}
