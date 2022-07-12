package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.Cart;
import com.patrick.springboot.mapper.CartMapper;
import com.patrick.springboot.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-11
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
