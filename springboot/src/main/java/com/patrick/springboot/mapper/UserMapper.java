package com.patrick.springboot.mapper;

import com.patrick.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-04-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
