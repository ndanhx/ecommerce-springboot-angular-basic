package com.nguyenduyanh.ecommerce.services.auth;

import com.nguyenduyanh.ecommerce.dto.SignupRequest;
import com.nguyenduyanh.ecommerce.dto.UserDto;
import com.nguyenduyanh.ecommerce.emuns.OrderStatus;
import com.nguyenduyanh.ecommerce.emuns.UserRole;
import com.nguyenduyanh.ecommerce.entity.Order;
import com.nguyenduyanh.ecommerce.entity.User;
import com.nguyenduyanh.ecommerce.repository.OrderRepository;
import com.nguyenduyanh.ecommerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        User createUser = userRepository.save(user);

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);
        UserDto userDto = new UserDto();
        userDto.setId(createUser.getId());
        return userDto;

    }


    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void CreateAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }

}
