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
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-06-18
 */
@Getter
@Setter
  @TableName("consum_records")
@ApiModel(value = "ConsumRecords对象", description = "")
public class ConsumRecords implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("登陆id")
      private Integer loginId;

      @ApiModelProperty("金额")
      private BigDecimal money;

      @ApiModelProperty("交易类型")
      private String type;

      @ApiModelProperty("消费商家id")
      private Integer sellerId;

      @ApiModelProperty("消费者id")
      private Integer buyerId;

      @ApiModelProperty("时间")
      private String time;

      @TableField(exist = false)
      private String sellerName;

      @TableField(exist = false)
      private String buyerName;

      @TableField(exist = false)
      private String currentName;
}
