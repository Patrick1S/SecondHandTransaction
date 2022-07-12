package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-06-17
 */
@Getter
@Setter
  @ApiModel(value = "Evaluate对象", description = "")
public class Evaluate implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("登陆id")
      private Integer loginId;

      @ApiModelProperty("商家id")
      private Integer userId;

      @ApiModelProperty("商品")
      private Integer goodsId;

      @ApiModelProperty("评论")
      private String comment;

      @ApiModelProperty("商品得分")
      private Integer goodsPoints;

      @ApiModelProperty("商家得分")
      private Integer sellerPoints;

      @TableField(exist = false)
      private String buyerName;

      @TableField(exist = false)
      private String goodsName;

      @TableField(exist = false)
      private String goodsImg;


}
