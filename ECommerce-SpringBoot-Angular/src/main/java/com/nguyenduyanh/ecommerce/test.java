package com.nguyenduyanh.ecommerce;

import com.nguyenduyanh.ecommerce.emuns.OrderStatus;
import com.nguyenduyanh.ecommerce.entity.Order;
import com.nguyenduyanh.ecommerce.entity.User;
import com.nguyenduyanh.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


public class test {


    public static void main(String[] args) {
        User user = new User();
        user.setId(4L);
        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.Pending);
    }
}
