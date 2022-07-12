package com.patrick.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.patrick.springboot.entity.Goodscomment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Patrick
 * @since 2022-06-09
 */
@Mapper
public interface GoodscommentMapper extends BaseMapper<Goodscomment> {

//    @Select("select c.*,u.nickname,u.avatar_url from t_goodscomment c left join sys_user u on c.user_id = u.id where c.goods_id = #{goodsId}};")
//    List<Goodscomment> findGoodsCommentDetail(@Param("goodsId") Integer goodsId);
}
