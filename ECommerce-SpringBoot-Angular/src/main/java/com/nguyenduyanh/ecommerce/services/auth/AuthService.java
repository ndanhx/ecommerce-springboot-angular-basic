package com.nguyenduyanh.ecommerce.services.auth;

import com.nguyenduyanh.ecommerce.dto.SignupRequestDtoDto;
import com.nguyenduyanh.ecommerce.dto.UserDto;

public interface AuthService {

    public UserDto createUser(SignupRequestDtoDto signupRequest);

    public boolean hasUserWithEmail(String email);
}
