package com.nguyenduyanh.ecommerce.controller.admin;

import com.nguyenduyanh.ecommerce.dto.UserDto;
import com.nguyenduyanh.ecommerce.services.admin.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAccountController {
    private  final AccountService accountService;

    @GetMapping("/account/{userId}")
    public ResponseEntity<UserDto> getAccountByUserId(@PathVariable Long userId){
        UserDto userDto = accountService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<UserDto>> getAllAccounts(){
        List<UserDto> userDto = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
