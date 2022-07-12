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
import java.util.List;

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
  @ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("登陆id")
      private Integer loginId;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("订单编号")
      private String no;

      @ApiModelProperty("总金额")
      private BigDecimal totalPrice;

      @ApiModelProperty("订单状态")
      private String state;

      @ApiModelProperty("下单时间")
      private String time;

      @ApiModelProperty("付款时间")
      private String payTime;

      @ApiModelProperty("发货时间")
      private String deliveryTime;

      @ApiModelProperty("成交时间")
      private String dealTime;

      @ApiModelProperty("发货地址")
      private String address;

      @ApiModelProperty("消耗积分")
      private BigDecimal consPoints;

      @TableField(exist = false)
      private List<Cart> carts;

      @TableField(exist = false)
      private String username;

      @TableField(exist = false)
      private String nickname;

      @TableField(exist = false)
      private List<Goods> goods;

}
