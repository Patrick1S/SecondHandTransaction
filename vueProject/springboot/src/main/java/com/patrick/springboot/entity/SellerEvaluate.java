package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
  @TableName("seller_evaluate")
@ApiModel(value = "SellerEvaluate对象", description = "")
public class SellerEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商家id")
      private Integer sellerId;

      @ApiModelProperty("买家id")
      private Integer buyerId;

      @ApiModelProperty("商品")
      private Integer goodsId;

      @ApiModelProperty("评论")
      private String comment;

      @ApiModelProperty("买家得分")
      private Integer buyerPoints;

      @TableField(exist = false)
      private String buyerName;

      @TableField(exist = false)
      private String goodsName;

      @TableField(exist = false)
      private String goodsImg;

}
