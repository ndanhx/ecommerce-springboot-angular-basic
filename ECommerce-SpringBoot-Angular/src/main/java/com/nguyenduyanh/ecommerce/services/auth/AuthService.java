package com.nguyenduyanh.ecommerce.services.auth;

import com.nguyenduyanh.ecommerce.dto.SignupRequest;
import com.nguyenduyanh.ecommerce.dto.UserDto;

public interface AuthService {

    public UserDto createUser(SignupRequest signupRequest);

    public boolean hasUserWithEmail(String email);
}
