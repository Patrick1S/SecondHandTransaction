package com.patrick.springboot.config;


import com.patrick.springboot.config.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Bean
//    public HandlerInterceptor jwtInterceptor(){
//        return new JwtInterceptor();
//    }
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")    // 拦截所有请求，通过判断token是否合法,决定是否需要登录
                //.excludePathPatterns("/user/login","/user/register","/**/export","/**/import");
                .excludePathPatterns("/user/login","/user/register","/file/**");
    }


}
