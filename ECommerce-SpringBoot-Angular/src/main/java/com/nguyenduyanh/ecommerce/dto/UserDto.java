package com.nguyenduyanh.ecommerce.dto;

import com.nguyenduyanh.ecommerce.emuns.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole role;
}
