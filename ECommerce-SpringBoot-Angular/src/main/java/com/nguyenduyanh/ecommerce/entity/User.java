package com.nguyenduyanh.ecommerce.entity;

import com.nguyenduyanh.ecommerce.dto.UserDto;
import com.nguyenduyanh.ecommerce.emuns.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String name;

    private String email;

    private UserRole role;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public UserDto getUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setRole(role);
        userDto.setEmail(email);
        return userDto;
    }
}
