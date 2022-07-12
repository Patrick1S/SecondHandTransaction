package com.patrick.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.patrick.springboot.controller.dto.UserDto;
import com.patrick.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Patrick
 * @since 2022-04-08
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);

    User register(UserDto userDto);
}
