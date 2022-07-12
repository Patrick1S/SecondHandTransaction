package com.patrick.springboot.config;

import java.lang.annotation.*;

//自定义注解授权url访问权限，有则放行，无则不允许访问
//忽略接口的token限制
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {

}
