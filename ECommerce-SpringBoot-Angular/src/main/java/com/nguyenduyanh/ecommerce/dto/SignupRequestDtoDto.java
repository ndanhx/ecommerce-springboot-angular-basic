package com.nguyenduyanh.ecommerce.dto;

import lombok.Data;

@Data
public class SignupRequestDtoDto {
    private String email;
    private String password;
    private String name;
}
