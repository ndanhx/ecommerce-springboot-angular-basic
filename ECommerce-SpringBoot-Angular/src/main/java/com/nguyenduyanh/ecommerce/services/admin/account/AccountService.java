package com.nguyenduyanh.ecommerce.services.admin.account;

import com.nguyenduyanh.ecommerce.dto.UserDto;

import java.util.List;

public interface AccountService {

    List<UserDto> getAllAccounts();

    UserDto getUserById(Long userId);

}
