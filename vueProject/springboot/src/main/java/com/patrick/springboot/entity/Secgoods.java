package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-06-09
 */
@Data
@ApiModel(value = "Secgoods对象", description = "")
public class Secgoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("价格")
      private BigDecimal price;

      @ApiModelProperty("商品描述")
      private String description;

      @ApiModelProperty("上传用户")
      private String user;

      @ApiModelProperty("图片")
      private String img;

      @ApiModelProperty("上架时间")
      private String time;


}
