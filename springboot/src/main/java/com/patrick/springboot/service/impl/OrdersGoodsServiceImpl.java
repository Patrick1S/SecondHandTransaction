package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.OrdersGoods;
import com.patrick.springboot.mapper.OrdersGoodsMapper;
import com.patrick.springboot.service.IOrdersGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-12
 */
@Service
public class OrdersGoodsServiceImpl extends ServiceImpl<OrdersGoodsMapper, OrdersGoods> implements IOrdersGoodsService {

}
