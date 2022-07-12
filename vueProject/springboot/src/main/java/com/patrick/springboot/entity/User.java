package com.patrick.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Patrick
 * @since 2022-04-08
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("昵称")
      private String nickname;

      @ApiModelProperty("性别")
      private String sex;


      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("地址")
      private String address;

      @ApiModelProperty("银行账户")
      private String bankAccount;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("头像")
      private String avatarUrl;

      @ApiModelProperty("角色")
      private String role;

      @ApiModelProperty("账户余额")
      private BigDecimal account;

      @ApiModelProperty("用户积分")
      private BigDecimal points;

      @ApiModelProperty("用户状态")
      private String  state;

      @ApiModelProperty("商家等级")
      private String  grade;


}
