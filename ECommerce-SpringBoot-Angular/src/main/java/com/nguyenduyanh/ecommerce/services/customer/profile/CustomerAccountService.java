package com.nguyenduyanh.ecommerce.services.customer.profile;

import com.nguyenduyanh.ecommerce.dto.UserDto;

import java.io.IOException;

public interface CustomerAccountService {

    UserDto getAccountByUserId(Long userId);

    UserDto updateAccountByUserId(Long userId, UserDto userDto) throws IOException;


}
