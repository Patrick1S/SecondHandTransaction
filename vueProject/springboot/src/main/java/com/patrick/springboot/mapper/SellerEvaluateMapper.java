package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.entity.Evaluate;
import com.patrick.springboot.entity.SellerEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-17
 */
@Mapper
public interface SellerEvaluateMapper extends BaseMapper<SellerEvaluate> {


    Page<Evaluate> frontPageSeller(@Param("page") Page<Evaluate> page, @Param("userId") Integer userId);

    Page<Evaluate> frontPageBuyer(@Param("page") Page<Evaluate> page, @Param("userId") Integer userId);
}
