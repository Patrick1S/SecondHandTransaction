package com.patrick.springboot.mapper;

import com.patrick.springboot.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-05-14
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
