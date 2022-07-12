package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-11
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    Page<Cart> frontPage(Page<Cart> page, @Param("loginId") Integer loginId, @Param("role") String role, @Param("name") String name, @Param("sellerId") Integer sellerId);

    Page<Cart> Page(Page<Cart> page, Integer loginId, String name);

//    List<Cart> sellersGoods(@Param("loginId") Integer loginId, @Param("role") String role, @Param("sellerId") Integer sellerId);
}
