package com.patrick.springboot.controller.dto;

import com.patrick.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 *
 *  接受前端登陆请求的参数
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private String email;
    private String phone;
    private String address;

    private String avatarUrl;
    private String token;
    private String state;
    private String grade;

    private String role;
    private List<Menu> menus;
}
