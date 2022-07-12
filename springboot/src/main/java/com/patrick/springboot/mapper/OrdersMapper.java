package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.entity.Orders;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-11
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    Page<Orders> page(Page<Orders> page, @Param("no")String no, @Param("role") String role, @Param("userId") Integer id,@Param("state")String state);

    Page<Orders> frontPage(Page<Orders> page, @Param("no")String no, @Param("role") String role, @Param("loginId") Integer id, @Param("state")String state);


    Page<Orders> frontPageOrders(Page<Orders> page, @Param("no")String no, @Param("role") String role, @Param("loginId") Integer id);

    Page<Orders> frontPageTransOrders(Page<Orders> page, @Param("no")String no, @Param("role") String role, @Param("sellerId") Integer id, @Param("state")String state);

    Page<Orders> frontPageAfterSales(Page<Orders> page, @Param("no")String no, @Param("role") String role, @Param("loginId") Integer id, @Param("state")String state);

//    Object frontPageAfterSales(Page<Object> objectPage, String no, String role, Integer sellerId, String state);

//    Object frontPageTransOrders(Page<Object> objectPage, String no, String role, Integer loginId, String state);

//    Object frontPageOrders(Page<Object> objectPage, String no, String role, Integer loginId);
}
