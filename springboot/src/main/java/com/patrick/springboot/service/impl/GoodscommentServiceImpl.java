package com.patrick.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.patrick.springboot.entity.Goodscomment;
import com.patrick.springboot.mapper.GoodscommentMapper;
import com.patrick.springboot.service.IGoodscommentService;
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
public class GoodscommentServiceImpl extends ServiceImpl<GoodscommentMapper, Goodscomment> implements IGoodscommentService {
//    @Resource
//    private GoodscommentMapper goodscommentMapper;
//
//    @Override
//    public List<Goodscomment> findGoodsCommentDetail(Integer goodsId) {
//
//        return goodscommentMapper.findGoodsCommentDetail(goodsId);
//    }
}
