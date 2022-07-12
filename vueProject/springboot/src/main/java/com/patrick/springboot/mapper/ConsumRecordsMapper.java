package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.entity.ConsumRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-18
 */
@Mapper
public interface ConsumRecordsMapper extends BaseMapper<ConsumRecords> {

//    Object frontPageBills(Page<Object> objectPage, String role, Integer loginId);


    Page<ConsumRecords>  frontPageBills(Page<ConsumRecords> page, @Param("role") String role, @Param("loginId") Integer id);

}
