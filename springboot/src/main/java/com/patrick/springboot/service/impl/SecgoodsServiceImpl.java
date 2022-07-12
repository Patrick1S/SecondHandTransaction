package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.Secgoods;
import com.patrick.springboot.mapper.SecgoodsMapper;
import com.patrick.springboot.service.ISecgoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-09
 */
@Service
public class SecgoodsServiceImpl extends ServiceImpl<SecgoodsMapper, Secgoods> implements ISecgoodsService {

}
