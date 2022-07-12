package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-08
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    Page<Goods> page(Page<Goods> page, @Param("name") String name);

    Page<Goods> frontPage(Page<Goods> page, @Param("name") String name, @Param("state") String state);

    Page<Goods> shopWindowPage(@Param("page") Page<Goods> page, @Param("name") String name, @Param("userId") Integer userId, @Param("state") String state);

//    Object frontPage(Page<Object> objectPage, String name, String state);

//    Object page(Page<Object> objectPage, String name);
}
