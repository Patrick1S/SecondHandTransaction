package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-06-11
 */
@Getter
@Setter
  @ApiModel(value = "Cart对象", description = "")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品id")
      private Integer goodsId;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("登陆id")
      private Integer loginId;

      @ApiModelProperty("商品数量")
      private Integer num;

      @ApiModelProperty("添加时间")
      private String time;

      @TableField(exist = false)
      private String goodsName;

      @TableField(exist = false)
      private String goodsImg;

      @TableField(exist = false)
      private BigDecimal price;

      @TableField(exist = false)
      private Integer store;

      @TableField(exist = false)
      private String unit;

      @TableField(exist = false)
      private String username;

      @TableField(exist = false)
      private String nickname;

      @TableField(exist = false)
      private String transport;

      @TableField(exist = false)
      private String sellerName;


}
