package com.patrick.springboot.mapper;

import com.patrick.springboot.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-13
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {

}
