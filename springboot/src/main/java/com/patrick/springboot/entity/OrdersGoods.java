package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-06-12
 */
@Getter
@Setter
  @TableName("orders_goods")
@ApiModel(value = "OrdersGoods对象", description = "")
public class OrdersGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

      @ApiModelProperty("订单id")
      private Integer orderId;

      @ApiModelProperty("商品uid")
      private Integer goodsId;

      @ApiModelProperty("商品数量")
      private Integer num;


}
