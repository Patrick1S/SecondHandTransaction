package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.UserAddress;
import com.patrick.springboot.mapper.UserAddressMapper;
import com.patrick.springboot.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-13
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

}
