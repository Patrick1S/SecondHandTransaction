package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.Goods;
import com.patrick.springboot.mapper.GoodsMapper;
import com.patrick.springboot.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-08
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
