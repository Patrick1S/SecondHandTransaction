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
 * @since 2022-06-08
 */
@Getter
@Setter
  @ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("价格")
      private BigDecimal price;

      @ApiModelProperty("商品描述")
      private String description;

      @ApiModelProperty("单位")
      private String unit;

      @ApiModelProperty("库存")
      private Integer store;

      @ApiModelProperty("图片")
      private String img;

      @ApiModelProperty("上架时间")
      private String time;

      @ApiModelProperty("商品发布者")
      private Integer userId;

      @ApiModelProperty("交易方式")
      private String transport;


      @ApiModelProperty("商品状态")
      private String state;


      @TableField(exist = false)
      private Integer num;

      @TableField(exist = false)
      private String nickname;

}
